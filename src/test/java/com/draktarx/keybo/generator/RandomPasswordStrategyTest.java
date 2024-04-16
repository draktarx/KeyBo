package com.draktarx.keybo.generator;

import com.draktarx.keybo.criteria.CriteriaRandomStrategy;
import com.draktarx.keybo.exceptions.PasswordGeneratorException;
import com.draktarx.keybo.rules.PasswordRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RandomPasswordStrategyTest {

    private CriteriaRandomStrategy criteria;


    @BeforeEach
    public void setUpBeforeClass() {
        criteria = new CriteriaRandomStrategy(16, true, true, true);
    }

    @Test
    void passwordShouldNotBeNull() throws PasswordGeneratorException {
        PasswordGenerator generator = new PasswordGenerator(new RandomPasswordStrategy(criteria));
        String password = generator.generatePassword();
        assertNotNull(password, "La contraseña generada no debe ser nula.");
    }

    @Test
    void passwordShouldHaveSameLength() throws PasswordGeneratorException {
        PasswordGenerator generator = new PasswordGenerator(new RandomPasswordStrategy(criteria));
        String password = generator.generatePassword();
        assertEquals(criteria.passwordLength(), password.length(), "La longitud no coincide");
    }

    @Test
    void passwordLengthShouldBeGreaterOrEqualThan8() throws PasswordGeneratorException {
        PasswordGenerator generator = new PasswordGenerator(new RandomPasswordStrategy(criteria));
        String password = generator.generatePassword();
        assertTrue(password.length() >= PasswordRules.LENGTH_MIN, "La longitud de la contraseña debe ser mayor que 8.");
    }

    @Test
    void passwordLengthShouldBeSmallerOrEqualThan32() throws PasswordGeneratorException {
        PasswordGenerator generator = new PasswordGenerator(new RandomPasswordStrategy(criteria));
        String password = generator.generatePassword();
        assertTrue(password.length() <= PasswordRules.LENGTH_MAX, "La longitud de la contraseña debe ser menor que 32.");
    }

    @Test
    void passwordShouldContainUpperCaseIfRequired() throws PasswordGeneratorException {
        PasswordGenerator generator = new PasswordGenerator(new RandomPasswordStrategy(new CriteriaRandomStrategy(16,
                true, false, false)));
        String password = generator.generatePassword();
        boolean hasUpperCase = password.chars()
                .mapToObj(c -> (char) c)
                .anyMatch(Character::isUpperCase);
        assertTrue(hasUpperCase, "La contraseña debe contener al menos una mayúscula.");
    }

    @Test
    void passwordShouldContainNumberIfRequired() throws PasswordGeneratorException {
        PasswordGenerator generator = new PasswordGenerator(new RandomPasswordStrategy(new CriteriaRandomStrategy(16,
                false, true, false)));
        String password = generator.generatePassword();
        boolean hasNumber = password.chars()
                .mapToObj(c -> (char) c)
                .anyMatch(Character::isDigit);
        assertTrue(hasNumber, "La contraseña debe contener al menos un número.");
    }

    @Test
    void passwordShouldContainSymbolIfRequired() throws PasswordGeneratorException {
        PasswordGenerator generator = new PasswordGenerator(new RandomPasswordStrategy(new CriteriaRandomStrategy(16,
                false, false, true)));
        String password = generator.generatePassword();
        boolean hasSymbol = password.chars()
                .mapToObj(c -> (char) c)
                .anyMatch(c -> PasswordRules.SYMBOLS.indexOf(c) >= 0);
        assertTrue(hasSymbol, "La contraseña debe contener al menos un símbolo.");
    }

}
