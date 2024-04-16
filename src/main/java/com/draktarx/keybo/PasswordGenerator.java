package com.draktarx.keybo;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()_+-";
    private static final Integer LENGTH_MIN = 8;
    private static final Integer LENGTH_MAX = 32;
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomPassword(int passwordLength, boolean hasUpperCase, boolean hasNumbers,
                                                boolean hasSymbols) {
        String password;
        if (!isPasswordLengthValid(passwordLength)) {
            throw new IllegalArgumentException();
        } else {
            StringBuilder validCharactersBuilder = new StringBuilder(LOWER_CASE);
            StringBuilder passwordBuilder = new StringBuilder();
            if (hasUpperCase) validCharactersBuilder.append(UPPER_CASE);
            if (hasNumbers) validCharactersBuilder.append(NUMBERS);
            if (hasSymbols) validCharactersBuilder.append(SYMBOLS);
            for (int i = 0; i < passwordLength; i++) {
                passwordBuilder.append(validCharactersBuilder.charAt(random.nextInt(validCharactersBuilder.length())));
            }
            password = passwordBuilder.toString();
        }
        System.out.println("Password: " + password);
        return password;
    }

    private static boolean isPasswordLengthValid(int passwordLength) {
        return passwordLength >= LENGTH_MIN && passwordLength <= LENGTH_MAX;
    }

    public static String getSYMBOLS() {
        return SYMBOLS;
    }

    public static Integer getLengthMin() {
        return LENGTH_MIN;
    }

    public static Integer getLengthMax() {
        return LENGTH_MAX;
    }
}
