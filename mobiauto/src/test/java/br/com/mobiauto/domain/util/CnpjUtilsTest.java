package br.com.mobiauto.domain.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CnpjUtilsTest {

    @Test
    void deveValidaEFormataCNPJ() {
        String cnpj = "12345678000195";
        String expected = "12.345.678/0001-95";
        String result = CnpjUtils.validaEFormataCNPJ(cnpj);
        assertEquals(expected, result);
    }

    @Test
    void testIsValidCNPJ() {
        String cnpj = "12345678000195";
        assertTrue(CnpjUtils.isValidCNPJ(cnpj));
    }

    @Test
    void testFormatarCNPJ() {
        String cnpj = "12345678000195";
        String expected = "12.345.678/0001-95";
        String result = CnpjUtils.formatarCNPJ(cnpj);
        assertEquals(expected, result);
    }

    @Test
    void deveCompletarCNPJComZerosAEsquerda() {
        String cnpj = "12345678000195";
        String expected = "12345678000195";
        StringBuilder result = CnpjUtils.completarCNPJComZerosAEsquerda(cnpj);
        assertEquals(expected, result.toString());
    }

    @Test
    void deveRemoverPontuacaoCNPJ() {
        String cnpj = "12.345.678/0001-95";
        String expected = "12345678000195";
        String result = CnpjUtils.removerPontuacaoCNPJ(cnpj);
        assertEquals(expected, result);
    }
}