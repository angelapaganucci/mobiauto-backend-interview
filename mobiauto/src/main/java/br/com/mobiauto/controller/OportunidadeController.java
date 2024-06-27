package br.com.mobiauto.controller;

import br.com.mobiauto.domain.dto.OportunidadeDTO;
import br.com.mobiauto.domain.dto.UsuarioDTO;
import br.com.mobiauto.domain.model.Usuario;
import br.com.mobiauto.domain.service.OportunidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/oportunidades")
public class OportunidadeController {

    private final OportunidadeService oportunidadeService;

    @PostMapping("/atribuir/{oportunidadeId}")
    public ResponseEntity<OportunidadeDTO> atribuirOportunidade(@PathVariable Long oportunidadeId, @RequestBody Usuario assistente) {
        OportunidadeDTO oportunidadeDTO = oportunidadeService.atribuirOportunidade(oportunidadeId, assistente);
        return ResponseEntity.ok(oportunidadeDTO);
    }

    @PostMapping("/transferir/{oportunidadeId}")
    public ResponseEntity<OportunidadeDTO> transferirOportunidade(@PathVariable Long oportunidadeId, @RequestBody Usuario novoResponsavel, Usuario usuarioAtual) {
        OportunidadeDTO oportunidadeDTO = oportunidadeService.transferirOportunidade(oportunidadeId, novoResponsavel, usuarioAtual);
        return ResponseEntity.ok(oportunidadeDTO);
    }

    @GetMapping
    public ResponseEntity<List<OportunidadeDTO>> buscarTodasAsOportunidades(UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(oportunidadeService.buscarTodasAsOportunidades(usuarioDTO));
    }

    @PostMapping
    public ResponseEntity<OportunidadeDTO> criarOportunidade(@RequestBody OportunidadeDTO oportunidadeDTO, UsuarioDTO usuarioDTO) {
        OportunidadeDTO oportunidade = oportunidadeService.criarOportunidade(oportunidadeDTO, usuarioDTO);
        return new ResponseEntity<>(oportunidade, HttpStatus.CREATED);

    }
}
