package com.draktarx.keybo.criteria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CriteriaRandomStrategyTest {

    @Test
    void shouldThrowExceptionIfPasswordLengthIsNotValid() {
        assertThrows(IllegalArgumentException.class, () -> new CriteriaRandomStrategy(5, true, true, true));
    }
}