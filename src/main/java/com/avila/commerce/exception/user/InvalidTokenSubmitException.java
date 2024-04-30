package com.avila.commerce.exception.user;

public class InvalidTokenSubmitException extends RuntimeException {
    public InvalidTokenSubmitException() {
        super("Unauthorized access");
    }
}