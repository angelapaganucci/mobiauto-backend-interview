package br.com.mobiauto.domain.util;

import static org.apache.commons.lang3.StringUtils.leftPad;

public class CnpjUtils {
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public static String validaEFormataCNPJ(String cnpj) {
        if (!isValidCNPJ(cnpj)) {
            return cnpj;
        }
        return formatarCNPJ(cnpj);
    }

    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj == null) {
            return false;
        }
        cnpj = cnpj.trim().replace(".", "").replace("-", "").replace("/", "");

        if (cnpj.length() != 14) {
            return false;
        }

        for (int j = 0; j < 14; j++) {
            if (padLeft(Integer.toString(j), Character.forDigit(j, 14)).equals(cnpj)) {
                return false;
            }
        }

        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digito1 + digito2);
    }

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static String formatarCNPJ(String cnpj) {
        return completarCNPJComZerosAEsquerda(cnpj).insert(2, '.').insert(6, '.').insert(10, '/').insert(15, '-').toString();
    }

    public static StringBuilder completarCNPJComZerosAEsquerda(String cnpj) {
        return new StringBuilder(leftPad(cnpj, 14, "0"));
    }

    private static String padLeft(String text, char character) {
        return String.format("%11s", text).replace(' ', character);
    }

    public static String removerPontuacaoCNPJ(String cnpj) {
        return cnpj.replaceAll("\\D", "");
    }

}
