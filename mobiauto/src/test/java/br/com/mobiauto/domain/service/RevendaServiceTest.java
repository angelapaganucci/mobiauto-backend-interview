package br.com.mobiauto.domain.service;

import br.com.mobiauto.domain.dto.RevendaDTO;
import br.com.mobiauto.domain.model.Revenda;
import br.com.mobiauto.domain.repository.RevendaRepository;
import br.com.mobiauto.domain.validator.RevendaValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RevendaServiceTest {

    @InjectMocks
    private RevendaService revendaService;

    @Mock
    private RevendaRepository revendaRepository;

    @Mock
    private RevendaValidator revendaValidator;

    @Test
    void deveCriarRevenda() {
        RevendaDTO revendaDTO = new RevendaDTO();
        revendaDTO.setCnpj("12345678901234");
        Revenda revenda = new Revenda();

        doNothing().when(revendaValidator).validate(any(RevendaDTO.class));
        when(revendaRepository.save(any(Revenda.class))).thenReturn(revenda);

        RevendaDTO result = revendaService.criarRevenda(revendaDTO);

        assertNotNull(result);
    }

    @Test
    void deveAtualizarRevenda() {
        Long id = 1L;
        RevendaDTO revendaDTO = new RevendaDTO();
        revendaDTO.setCnpj("12345678901234");
        Revenda revenda = new Revenda();

        when(revendaRepository.findById(id)).thenReturn(Optional.of(revenda));
        when(revendaRepository.save(any(Revenda.class))).thenReturn(revenda);

        RevendaDTO result = revendaService.atualizarRevenda(id, revendaDTO);

        assertNotNull(result);
    }

    @Test
    void deveBuscarTodasRevendas() {
        List<Revenda> revendas = Arrays.asList(new Revenda(), new Revenda());

        when(revendaRepository.findAll()).thenReturn(revendas);

        List<RevendaDTO> result = revendaService.buscarTodasRevendas();

        assertNotNull(result);
        assertEquals(revendas.size(), result.size());
    }

}