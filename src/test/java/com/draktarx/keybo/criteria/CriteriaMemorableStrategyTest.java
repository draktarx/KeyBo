package com.draktarx.keybo.criteria;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CriteriaMemorableStrategyTest {

    @Test
    void shouldThrowExceptionWhenUserWordsIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new CriteriaMemorableStrategy(List.of(), false, false));
    }

}