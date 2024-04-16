package com.draktarx.keybo.generator;

import com.draktarx.keybo.exceptions.PasswordGeneratorException;

public interface PasswordStrategy {
    String generatePassword() throws PasswordGeneratorException;
}
