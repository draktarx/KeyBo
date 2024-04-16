package com.draktarx.keybo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordGeneratorTest {

    private final int expectedLength = 28;

    @Test
    void passwordShouldNotBeNull() {
        String password = PasswordGenerator.generateRandomPassword(expectedLength, true, true, true);
        assertNotNull(password, "La contraseña generada no debe ser nula.");
    }

    @Test
    void passwordShouldHaveSameLength() {
        String password = PasswordGenerator.generateRandomPassword(expectedLength, true, true, true);
        assertEquals(expectedLength, password.length(), "La longitud no coincide");
    }

    @Test
    void passwordLengthShouldBeGreaterOrEqualThan8() {
        String password = PasswordGenerator.generateRandomPassword(expectedLength, true, true, true);
        assertTrue(password.length() >= PasswordGenerator.getLengthMin(), "La longitud de la contraseña debe ser mayor que 8.");
    }

    @Test
    void passwordLengthShouldBeSmallerOrEqualThan32() {
        String password = PasswordGenerator.generateRandomPassword(expectedLength, true, true, true);
        assertTrue(password.length() <= PasswordGenerator.getLengthMax(), "La longitud de la contraseña debe ser menor que 32.");
    }

    @Test
    void passwordShouldContainUpperCaseIfRequired() {
        String password = PasswordGenerator.generateRandomPassword(expectedLength, true, false, false);
        boolean hasUpperCase = password.chars()
                .mapToObj(c -> (char) c)
                .anyMatch(Character::isUpperCase);
        assertTrue(hasUpperCase, "La contraseña debe contener al menos una mayúscula.");
    }

    @Test
    void passwordShouldContainNumberIfRequired() {
        String password = PasswordGenerator.generateRandomPassword(expectedLength, false, true, false);
        boolean hasNumber = password.chars()
                .mapToObj(c -> (char) c)
                .anyMatch(Character::isDigit);
        assertTrue(hasNumber, "La contraseña debe contener al menos un número.");
    }

    @Test
    void passwordShouldContainSymbolIfRequired() {
        String password = PasswordGenerator.generateRandomPassword(expectedLength, false, false, true);
        boolean hasSymbol = password.chars()
                .mapToObj(c -> (char) c)
                .anyMatch(c -> PasswordGenerator.getSYMBOLS().indexOf(c) >= 0);
        assertTrue(hasSymbol, "La contraseña debe contener al menos un símbolo.");
    }
}
