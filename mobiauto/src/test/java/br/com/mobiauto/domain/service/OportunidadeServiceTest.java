package br.com.mobiauto.domain.service;

import br.com.mobiauto.domain.dto.OportunidadeDTO;
import br.com.mobiauto.domain.dto.UsuarioDTO;
import br.com.mobiauto.domain.model.Cargo;
import br.com.mobiauto.domain.model.Oportunidade;
import br.com.mobiauto.domain.model.StatusOportunidade;
import br.com.mobiauto.domain.model.Usuario;
import br.com.mobiauto.domain.repository.OportunidadeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OportunidadeServiceTest {

    @InjectMocks
    private OportunidadeService oportunidadeService;

    @Mock
    private OportunidadeRepository oportunidadeRepository;

    @Test
    void deveBuscarTodasAsOportunidades() {
        when(oportunidadeRepository.findAllByResponsavel(1L))
                .thenReturn(List.of(OportunidadeDTO.fromOportunidade(
                        new Oportunidade(1L, "123", StatusOportunidade.NOVO, null,
                                null, null, null, null, null,
                                null))));

        List<OportunidadeDTO> result = oportunidadeService.buscarTodasAsOportunidades(
                UsuarioDTO.fromUsuario(new Usuario(1L, "123", null, null, null,
                        null, null, null)));

        assertEquals(1, result.size());
        assertNotNull(result.get(0));
    }

    @Test
    void deveCriarOportunidade() {
        Oportunidade oportunidade = new Oportunidade(1L, "123", StatusOportunidade.NOVO, null,
                null, null, null, null, null,
                null);
        when(oportunidadeRepository.save(any())).thenReturn(oportunidade);

        OportunidadeDTO result = oportunidadeService.criarOportunidade(
                OportunidadeDTO.fromOportunidade(oportunidade),
                UsuarioDTO.fromUsuario(new Usuario(1L, "123", null, null, null,
                        null, null, null)));

        assertNotNull(result);
        assertEquals("123", result.getCodigoIdentificador());
    }

    @Test
    void deveApresentarExcecaoQuandoNaoEncontrarOportunidade() {
        doThrow(new ResourceNotFoundException("Oportunidade não encontrada"))
                .when(oportunidadeRepository).findById(1L);

        assertThrows(ResourceNotFoundException.class,
                () -> oportunidadeService.atribuirOportunidade(1L, new Usuario(1L, "123",
                        null, null, null, null, null, null)));
    }

    @Test
    void deveAtribuirOportunidade() {
        Long oportunidadeId = 1L;
        Usuario usuario = new Usuario(1L, "123", null, null, null, null, null, null);
        Oportunidade oportunidade = new Oportunidade(1L, "123", StatusOportunidade.NOVO, null, null, null, null, null, null, null);

        when(oportunidadeRepository.findById(oportunidadeId)).thenReturn(Optional.of(oportunidade));
        when(oportunidadeRepository.save(any(Oportunidade.class))).thenAnswer(i -> i.getArguments()[0]);

        OportunidadeDTO result = oportunidadeService.atribuirOportunidade(oportunidadeId, usuario);

        assertNotNull(result);
        assertEquals(usuario.getId(), result.getResponsavel().getId());
    }

    @Test
    void deveTransferirOportunidade() {
        Long oportunidadeId = 1L;
        Usuario usuarioAtual = new Usuario(1L, "123", "", "", "", Cargo.GERENTE, null, null);
        Usuario novoResponsavel = new Usuario(2L, "456", "", "", "", Cargo.ASSISTENTE, null, null);
        Oportunidade oportunidade = new Oportunidade(1L, "123", StatusOportunidade.NOVO, null, null, null, null, null, null, null);

        when(oportunidadeRepository.findById(oportunidadeId)).thenReturn(Optional.of(oportunidade));
        when(oportunidadeRepository.save(any(Oportunidade.class))).thenAnswer(i -> i.getArguments()[0]);

        OportunidadeDTO result = oportunidadeService.transferirOportunidade(oportunidadeId, novoResponsavel, usuarioAtual);

        assertNotNull(result);
        assertEquals(novoResponsavel.getId(), result.getResponsavel().getId());
    }
}