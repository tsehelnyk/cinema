package com.dev.cinema.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message) {
        throw new RuntimeException(message);
    }
}
