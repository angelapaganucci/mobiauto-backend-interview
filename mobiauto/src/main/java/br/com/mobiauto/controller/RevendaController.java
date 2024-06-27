package br.com.mobiauto.controller;

import br.com.mobiauto.domain.dto.RevendaDTO;
import br.com.mobiauto.domain.service.RevendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/revendas")
public class RevendaController {

    private final RevendaService revendaService;

    @PostMapping
    public ResponseEntity<RevendaDTO> criarRevenda(@RequestBody RevendaDTO revendaDTO) {
        RevendaDTO revenda = revendaService.criarRevenda(revendaDTO);
        return new ResponseEntity<>(revenda, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RevendaDTO> atualizarRevenda(@PathVariable Long id, @RequestBody RevendaDTO revenda) {
        RevendaDTO atualizarRevenda = revendaService.atualizarRevenda(id, revenda);
        return new ResponseEntity<>(atualizarRevenda, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RevendaDTO>> buscarTodasRevendas() {
        List<RevendaDTO> revendas = revendaService.buscarTodasRevendas();
        return new ResponseEntity<>(revendas, HttpStatus.OK);
    }
}