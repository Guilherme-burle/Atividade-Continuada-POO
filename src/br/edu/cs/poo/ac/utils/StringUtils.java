package br.edu.cs.poo.ac.utils;

public class StringUtils {

    public static boolean estaVazia(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean tamanhoExcedido(String str, int tamanho) {
        if (tamanho < 0) {
            return false;
        }
        if (str == null) {
            return tamanho > 0;
        }
        return str.length() > tamanho;
    }

    public static boolean emailValido(String email) {
        if (email == null) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    public static boolean telefoneValido(String tel) {
        if (tel == null) {
            return false;
        }
        String telRegex = "^\\(\\d{2}\\)\\d{8,9}$";
        return tel.matches(telRegex);
    }

    public static boolean tamanhoMenor(String str, int tamanho) {
        if (tamanho < 0) {
            return false;
        }
        if (str == null) {
            return tamanho > 0;
        }
        return str.length() < tamanho;
    }
}