package br.com.mobiauto.domain.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidatorTest {

    @Test
    void testIsValidPassword() {
        assertTrue(PasswordValidator.isValidPassword("Abcdef1@"));
        assertFalse(PasswordValidator.isValidPassword("abcdef1@"));
        assertFalse(PasswordValidator.isValidPassword("ABCDEF1@"));
        assertFalse(PasswordValidator.isValidPassword("Abcdefgh"));
        assertFalse(PasswordValidator.isValidPassword("12345678"));
        assertFalse(PasswordValidator.isValidPassword("ABCDEFGH@"));
        assertFalse(PasswordValidator.isValidPassword("abcdefgh@"));
    }
}