package com.rony.notepadbackend.exception;

public class InvalidPayloadException extends RuntimeException {
    public InvalidPayloadException(String message) {
        super(message);
    }
}
