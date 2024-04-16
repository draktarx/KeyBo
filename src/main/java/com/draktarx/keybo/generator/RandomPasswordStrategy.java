package com.draktarx.keybo.generator;

import com.draktarx.keybo.criteria.CriteriaRandomStrategy;
import com.draktarx.keybo.exceptions.PasswordGeneratorException;
import com.draktarx.keybo.rules.PasswordRules;

import java.security.SecureRandom;

public record RandomPasswordStrategy(CriteriaRandomStrategy criteria) implements PasswordStrategy {
    private static final SecureRandom random = new SecureRandom();

    @Override
    public String generatePassword() throws PasswordGeneratorException {
        try {
            StringBuilder validCharacters = generateValidCharacters();
            return generatePasswordFromValidCharacters(validCharacters);
        } catch (Exception e) {
            throw new PasswordGeneratorException("Unable to generate the random password. Log: " + e.getMessage());
        }
    }

    private String generatePasswordFromValidCharacters(StringBuilder validCharacters) {
        StringBuilder password = new StringBuilder(criteria.passwordLength());
        for (int i = 0; i < criteria.passwordLength(); i++) {
            int randomIndex = random.nextInt(validCharacters.length());
            password.append(validCharacters.charAt(randomIndex));
        }
        return password.toString();
    }

    private StringBuilder generateValidCharacters() {
        StringBuilder validCharacters = new StringBuilder(PasswordRules.LOWER_CASE);
        if (criteria().hasUpperCase()) {
            validCharacters.append(PasswordRules.UPPER_CASE);
        }
        if (criteria.hasNumbers()) {
            validCharacters.append(PasswordRules.NUMBERS);
        }
        if (criteria.hasSymbols()) {
            validCharacters.append(PasswordRules.SYMBOLS);
        }
        return validCharacters;
    }
}

