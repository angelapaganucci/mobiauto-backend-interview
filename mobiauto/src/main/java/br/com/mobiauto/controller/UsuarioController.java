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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO registerDTO) {
        if (isEmpty(this.usuarioRepository.findByEmail(registerDTO.email()))) {
            if (!PasswordValidator.isValidPassword(registerDTO.senha())) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Senha não atende aos requisitos mínimos de segurança.");
            }
            String senhaCriptografada = new BCryptPasswordEncoder().encode(registerDTO.senha());
            Usuario novoUsuario = new Usuario();
            novoUsuario.setCodigoIdentificador(registerDTO.codigoIdentificador());
            novoUsuario.setNome(registerDTO.nome());
            novoUsuario.setEmail(registerDTO.email());
            novoUsuario.setSenha(senhaCriptografada);
            novoUsuario.setCargo(registerDTO.cargo());
            novoUsuario.setRole(registerDTO.role());

            this.usuarioRepository.save(novoUsuario);

            String token = tokenService.gerarToken(novoUsuario);

            return ResponseEntity.ok(new LoginResponseDTO(novoUsuario.getNome(), token));
        }

        return ResponseEntity.badRequest().build();

    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO authenticationDTO) {
        Usuario usuario = this.usuarioRepository.findByEmail(authenticationDTO.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(authenticationDTO.senha(), usuario.getSenha())) {
            String token = this.tokenService.gerarToken(usuario);
            return ResponseEntity.ok(new LoginResponseDTO(usuario.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarTodos() {
        List<UsuarioDTO> usuarios = usuarioService.buscarTodos();
        return ResponseEntity.ok(usuarios);
    }

}