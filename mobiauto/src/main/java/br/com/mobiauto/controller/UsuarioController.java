package br.com.mobiauto.controller;

import br.com.mobiauto.domain.dto.AuthenticationDTO;
import br.com.mobiauto.domain.dto.LoginResponseDTO;
import br.com.mobiauto.domain.dto.RegisterDTO;
import br.com.mobiauto.domain.dto.UsuarioDTO;
import br.com.mobiauto.domain.model.Usuario;
import br.com.mobiauto.domain.repository.UsuarioRepository;
import br.com.mobiauto.domain.service.UsuarioService;
import br.com.mobiauto.domain.validator.PasswordValidator;
import br.com.mobiauto.infra.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO data) {
        if (this.usuarioRepository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }
        if (!PasswordValidator.isValidPassword(data.senha())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Senha não atende aos requisitos mínimos de segurança.");
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(data.senha());
        Usuario novoUsuario = new Usuario(data.codigoIdentificador(), data.nome(), data.email(), data.cargo(), data.revenda(), senhaCriptografada, data.role());

        this.usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data) {
        var userNamePassword = new UsernamePasswordAuthenticationToken((data.email()), data.senha());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarTodos() {
        List<UsuarioDTO> usuarios = usuarioService.buscarTodos();
        return ResponseEntity.ok(usuarios);
    }

}