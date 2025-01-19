package com.tahrioussama.employeerecordsmanagementsystem.config;

import java.util.regex.Pattern;

/**
 * Utility class for validation operations.
 */
public class ValidationUtils {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private ValidationUtils() {
        // Private constructor to prevent instantiation
    }

    /**
     * Validates an email address.
     *
     * @param email the email address to validate
     * @return true if the email is valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Validates that a string is not null or empty.
     *
     * @param value the string to validate
     * @param fieldName the name of the field being validated
     * @throws IllegalArgumentException if the string is null or empty
     */
    public static void validateNotNullOrEmpty(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
    }

    /**
     * Validates a positive ID value.
     *
     * @param id the ID to validate
     * @param fieldName the name of the field being validated
     * @throws IllegalArgumentException if the ID is not positive
     */
    public static void validatePositiveId(Long id, String fieldName) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(fieldName + " must be a positive number");
        }
    }
}

