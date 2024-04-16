package com.draktarx.keybo.generator;

import com.draktarx.keybo.exceptions.PasswordGeneratorException;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PasswordGeneratorTest {

    @Test
    public void usingRandomStrategy_GeneratesRandomPassword() throws PasswordGeneratorException {
        PasswordStrategy randomStrategy = mock(RandomPasswordStrategy.class);
        when(randomStrategy.generatePassword()).thenReturn("h(tVsSR-O!T$M)+^VFRq+RSjJ9Az");

        PasswordGenerator generator = new PasswordGenerator(randomStrategy);
        String password = generator.generatePassword();

        assertNotNull(password);
        assertTrue(Pattern.matches("[a-zA-Z0-9\\p{Punct}]*", password));
    }

    @Test
    public void usingMemorableStrategy_GeneratesMemorablePassword() throws PasswordGeneratorException {
        MemorablePasswordStrategy memorablePasswordStrategy = mock(MemorablePasswordStrategy.class);
        when(memorablePasswordStrategy.generatePassword()).thenReturn("Yellow8-Red3-Blue0-Gray8-Blue5");

        PasswordGenerator generator = new PasswordGenerator(memorablePasswordStrategy);
        String password = generator.generatePassword();

        assertNotNull(password);
        assertTrue(Pattern.matches("([A-Z]?[a-z]+\\d?-?)+", password));
    }

    @Test
    public void whenFails_throwPasswordGenerationException() throws PasswordGeneratorException {

        PasswordStrategy mockStrategy = mock(PasswordStrategy.class);
        when(mockStrategy.generatePassword()).thenThrow(new PasswordGeneratorException("Strategy failure"));
        PasswordGenerator generator = new PasswordGenerator(mockStrategy);
        PasswordGeneratorException expectedException = assertThrows(PasswordGeneratorException.class, generator::generatePassword);
        assertEquals(expectedException.getClass(), PasswordGeneratorException.class);

        verify(mockStrategy).generatePassword();
    }

    @Test
    void testPasswordGeneratorWithNullStrategy() {
        assertThrows(IllegalArgumentException.class, () -> new PasswordGenerator(null));
    }

    @Test
    void testGeneratePassword() throws PasswordGeneratorException {
        PasswordStrategy mockStrategy = mock(PasswordStrategy.class);
        when(mockStrategy.generatePassword()).thenReturn("1s5mZjbL");
        PasswordGenerator generator = new PasswordGenerator(mockStrategy);

        String password = generator.generatePassword();

        assertNotNull(password);
        assertFalse(password.isEmpty());
        assertEquals("1s5mZjbL", password);
    }

    @Test
    void testPasswordConsistency() throws PasswordGeneratorException {
        PasswordStrategy mockStrategy = mock(PasswordStrategy.class);
        when(mockStrategy.generatePassword()).thenReturn("ConsistentPassword");
        PasswordGenerator generator = new PasswordGenerator(mockStrategy);

        String password1 = generator.generatePassword();
        String password2 = generator.generatePassword();

        assertEquals(password1, password2);
    }

    @Test
    void testPasswordGenerationException() throws PasswordGeneratorException {
        PasswordStrategy mockStrategy = mock(PasswordStrategy.class);
        when(mockStrategy.generatePassword()).thenThrow(new PasswordGeneratorException("Error generating password"));
        PasswordGenerator generator = new PasswordGenerator(mockStrategy);

        assertThrows(PasswordGeneratorException.class, generator::generatePassword);
    }
}
