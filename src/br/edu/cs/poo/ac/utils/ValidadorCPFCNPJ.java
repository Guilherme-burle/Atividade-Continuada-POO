package br.edu.cs.poo.ac.utils;

public class ValidadorCPFCNPJ {

    private static final int TAMANHO_CPF = 11;
    private static final int TAMANHO_CNPJ = 14;

    private static final int[] PESOS_CPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] PESOS_CNPJ_1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] PESOS_CNPJ_2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public static ResultadoValidacaoCPFCNPJ validarCPFCNPJ(String cpfCnpj) {
        if (StringUtils.estaVazia(cpfCnpj)) {
            return new ResultadoValidacaoCPFCNPJ(false, false, ErroValidacaoCPFCNPJ.CPF_CNPJ_NAO_E_CPF_NEM_CNPJ);
        }

        String apenasDigitos = cpfCnpj.replaceAll("\\D+", "");

        if (apenasDigitos.length() == TAMANHO_CPF) {
            return validarCPF(apenasDigitos);
        } else if (apenasDigitos.length() == TAMANHO_CNPJ) {
            return validarCNPJ(apenasDigitos);
        } else {
            return new ResultadoValidacaoCPFCNPJ(false, false, ErroValidacaoCPFCNPJ.CPF_CNPJ_NAO_E_CPF_NEM_CNPJ);
        }
    }

    private static ResultadoValidacaoCPFCNPJ validarCPF(String cpf) {
        if (temTodosDigitosIguais(cpf)) {
            return new ResultadoValidacaoCPFCNPJ(false, false, ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_DV_INVALIDO);
        }

        Integer dv1 = calcularDigito(cpf.substring(0, 9), PESOS_CPF);
        Integer dv2 = calcularDigito(cpf.substring(0, 10), PESOS_CPF);

        if (cpf.equals(cpf.substring(0, 9) + dv1.toString() + dv2.toString())) {
            return new ResultadoValidacaoCPFCNPJ(true, false, null);
        } else {
            return new ResultadoValidacaoCPFCNPJ(false, false, ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_DV_INVALIDO);
        }
    }

    private static ResultadoValidacaoCPFCNPJ validarCNPJ(String cnpj) {
        if (temTodosDigitosIguais(cnpj)) {
            return new ResultadoValidacaoCPFCNPJ(false, false, ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_DV_INVALIDO);
        }

        Integer dv1 = calcularDigito(cnpj.substring(0, 12), PESOS_CNPJ_1);
        Integer dv2 = calcularDigito(cnpj.substring(0, 13), PESOS_CNPJ_2);

        if (cnpj.equals(cnpj.substring(0, 12) + dv1.toString() + dv2.toString())) {
            return new ResultadoValidacaoCPFCNPJ(false, true, null);
        } else {
            return new ResultadoValidacaoCPFCNPJ(false, false, ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_DV_INVALIDO);
        }
    }

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int i = 0; i < str.length(); i++) {
            soma += Integer.parseInt(str.substring(i, i + 1)) * peso[i + (peso.length - str.length())];
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : (11 - resto);
    }

    private static boolean temTodosDigitosIguais(String str) {
        char primeiro = str.charAt(0);
        for(char c : str.toCharArray()){
            if(c != primeiro) return false;
        }
        return true;
    }
}