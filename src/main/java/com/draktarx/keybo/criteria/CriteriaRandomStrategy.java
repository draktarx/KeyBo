package com.draktarx.keybo.criteria;

import com.draktarx.keybo.rules.PasswordRules;

public record CriteriaRandomStrategy(int passwordLength, boolean hasUpperCase, boolean hasNumbers, boolean hasSymbols) {

    public CriteriaRandomStrategy {
        if (passwordLength < PasswordRules.LENGTH_MIN || passwordLength > PasswordRules.LENGTH_MAX)
            throw new IllegalArgumentException("Invalid password length");
    }

}
