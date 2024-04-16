package com.draktarx.keybo.generator;

import com.draktarx.keybo.criteria.CriteriaMemorableStrategy;
import com.draktarx.keybo.exceptions.PasswordGeneratorException;
import com.draktarx.keybo.rules.PasswordRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MemorablePasswordStrategyTest {

    private static final List<String> USER_WORDS = Arrays.asList(
            "red", "blue", "green", "yellow", "orange", "purple", "pink", "brown", "gray"
    );
    private CriteriaMemorableStrategy criteria;

    @BeforeEach
    public void setUpBeforeClass() {
        criteria = new CriteriaMemorableStrategy(USER_WORDS, true, true);
    }

    @Test
    public void passwordShouldNotBeNull() throws PasswordGeneratorException {
        PasswordGenerator generator = new PasswordGenerator(new MemorablePasswordStrategy(criteria));
        String password = generator.generatePassword();
        assertNotNull(password, "La contraseña generada no debe ser nula.");
    }

    @Test
    public void passwordShouldIncludeUpperCaseWhenRequested() throws PasswordGeneratorException {
        PasswordGenerator generator = new PasswordGenerator(new MemorablePasswordStrategy(criteria));
        String password = generator.generatePassword();
        assertTrue(password.matches(".*[A-Z].*"), "La contraseña debe incluir al menos una letra mayúscula.");
    }

    @Test
    public void passwordShouldIncludeNumbersWhenRequested() throws PasswordGeneratorException {
        PasswordGenerator generator = new PasswordGenerator(new MemorablePasswordStrategy(criteria));
        String password = generator.generatePassword();
        assertTrue(password.matches(".*\\d.*"), "La contraseña debe incluir al menos un número.");
    }

    @Test
    public void passwordShouldContainHyphensBetweenWords() throws PasswordGeneratorException {
        PasswordGenerator generator = new PasswordGenerator(new MemorablePasswordStrategy(criteria));
        String password = generator.generatePassword();
        assertTrue(password.contains("-"), "La contraseña debe contener guiones entre palabras.");
    }

    @Test
    public void passwordShouldNotExceedMaximumLength() throws PasswordGeneratorException {
        PasswordGenerator generator = new PasswordGenerator(new MemorablePasswordStrategy(criteria));
        String password = generator.generatePassword();
        assertTrue(password.length() <= PasswordRules.LENGTH_MAX,
                "La contraseña no debe exceder la longitud máxima permitida.");
    }

    @Test
    public void passwordShouldBeRandomlyGenerated() throws PasswordGeneratorException {
        PasswordGenerator generator = new PasswordGenerator(new MemorablePasswordStrategy(criteria));
        String password1 = generator.generatePassword();
        String password2 = generator.generatePassword();
        assertNotEquals("Las contraseñas generadas en dos intentos distintos deben ser diferentes.", password1, password2);
    }

//    @Test
//    public void whenEmptyWordListProvided_thenIllegalArgumentExceptionIsThrown() {
//        assertThrows(PasswordGeneratorException.class, new MemorablePasswordStrategy(criteriaWithEmptyWords)::generatePassword);
//    }

}
