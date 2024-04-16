package com.draktarx.keybo.generator;

import com.draktarx.keybo.criteria.CriteriaMemorableStrategy;
import com.draktarx.keybo.exceptions.PasswordGeneratorException;
import com.draktarx.keybo.rules.PasswordRules;

import java.security.SecureRandom;

public record MemorablePasswordStrategy(CriteriaMemorableStrategy criteria) implements PasswordStrategy {

    private static final SecureRandom random = new SecureRandom();

    private static int separatorLength(StringBuilder currentPassword) {
        return !currentPassword.isEmpty() ? 1 : 0;
    }

    @Override
    public String generatePassword() throws PasswordGeneratorException {
        try {
            StringBuilder password = new StringBuilder();
            int wordsUsed = 0;

            while (canAddMoreWords(wordsUsed, password)) {
                String word = getNextWord();
                if (!canFitInPassword(password, word)) {
                    break;
                }
                addWordToPassword(password, word, shouldAddHyphen(wordsUsed));
                wordsUsed++;
            }

            validatePasswordLength(password);
            return password.toString();
        } catch (Exception e) {
            throw new PasswordGeneratorException("Unable to generate the random password. Log: " + e.getMessage());
        }
    }

    private boolean canAddMoreWords(int wordsUsed, StringBuilder password) {
        return wordsUsed < criteria.userWords().size() && password.length() < PasswordRules.LENGTH_MAX;
    }

    private String getNextWord() {
        String baseWord = selectRandomWord();
        return transformWord(baseWord);
    }

    private boolean canFitInPassword(StringBuilder currentPassword, String newWord) {
        int newLength = currentPassword.length() + newWord.length() + separatorLength(currentPassword);
        return newLength <= PasswordRules.LENGTH_MAX;
    }

    private void addWordToPassword(StringBuilder password, String word, boolean addHyphen) {
        if (addHyphen) {
            password.append("-");
        }
        password.append(word);
    }

    private boolean shouldAddHyphen(int wordsUsed) {
        return wordsUsed > 0;
    }

    private void validatePasswordLength(StringBuilder password) {
        if (password.isEmpty())
            throw new IllegalArgumentException("The requested configuration leads to a password exceeding the maximum allowed length.");
    }

    private String selectRandomWord() {
        return criteria.userWords().get(random.nextInt(criteria.userWords().size()));
    }

    private String transformWord(String word) {
        if (criteria.includeUpperCase()) {
            word = capitalize(word);
        }
        if (criteria.includeNumbers()) {
            char number = PasswordRules.NUMBERS.charAt(random.nextInt(PasswordRules.NUMBERS.length()));
            word += number;
        }
        return word;
    }

    private String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
}

