package com.example.deliveryapi.exception;

public class MenuAddFailedException extends RuntimeException{
    public MenuAddFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
