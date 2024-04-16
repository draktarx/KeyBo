package com.draktarx.keybo.generator;

import com.draktarx.keybo.exceptions.PasswordGeneratorException;

public class PasswordGenerator {

    private final PasswordStrategy strategy;

    public PasswordGenerator(PasswordStrategy strategy) {
        if (strategy == null) throw new IllegalArgumentException("Password strategy cannot be null.");
        this.strategy = strategy;
    }

    public String generatePassword() throws PasswordGeneratorException {
        String generatedPassword;
        generatedPassword = strategy.generatePassword();
        System.out.println("Password: " + generatedPassword);
        System.out.println("Length: " + generatedPassword.length());
        return generatedPassword;
    }

}