package com.avila.commerce.exception.user;

public class InvalidTokenGenerationException extends RuntimeException {
    public InvalidTokenGenerationException(String message) {
        super(message);
    }
}