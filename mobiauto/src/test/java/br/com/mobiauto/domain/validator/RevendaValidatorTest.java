package br.com.mobiauto.domain.validator;

import br.com.mobiauto.domain.dto.RevendaDTO;
import br.com.mobiauto.domain.repository.RevendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RevendaValidatorTest {

    @Mock
    private RevendaRepository revendaRepository;

    @InjectMocks
    private RevendaValidator revendaValidator;

    private RevendaDTO revendaDTO;

    @BeforeEach
    void setUp() {
        revendaDTO = new RevendaDTO();
        revendaDTO.setCodigoIdentificador("1234");
        revendaDTO.setCnpj("12345678000195");
        revendaDTO.setNomeSocial("Teste");
    }

    @Test
    void deveApresentarExcecaoQUandoCodigoIdentificadorJaExistir() {
        when(revendaRepository.existsByCodigoIdentificador(revendaDTO.getCodigoIdentificador())).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> revendaValidator.validate(revendaDTO));
    }

    @Test
    void deveApresentarExcecaoQUandoCNPJForInvalido() {
        revendaDTO.setCnpj("invalid");
        assertThrows(IllegalArgumentException.class, () -> revendaValidator.validate(revendaDTO));
    }

    @Test
    void deveApresentarExcecaoQuandoNomeSocialEstiverEmBranco() {
        revendaDTO.setNomeSocial("");
        assertThrows(IllegalArgumentException.class, () -> revendaValidator.validate(revendaDTO));
    }
}