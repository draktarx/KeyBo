package com.draktarx.keybo.criteria;

import java.util.List;

public record CriteriaMemorableStrategy(List<String> userWords, boolean includeUpperCase, boolean includeNumbers) {

    public CriteriaMemorableStrategy {
        if (userWords == null || userWords.isEmpty())
            throw new IllegalArgumentException("User word list cannot be null or empty");
    }

}
