package org.example.exceptions;

public class MaterialNotAvailableException extends RuntimeException {
    public MaterialNotAvailableException(String message) {
        super(message);
    }
}
