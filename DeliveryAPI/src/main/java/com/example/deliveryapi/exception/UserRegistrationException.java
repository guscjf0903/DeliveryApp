package com.example.deliveryapi.exception;

public class UserRegistrationException extends RuntimeException{
    public UserRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
