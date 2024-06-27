package br.com.mobiauto.domain.validator;

public class PasswordValidator {

    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*@#$%^&+=])(?=\\S+$).{8,15}$";

    public static boolean isValidPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }
}