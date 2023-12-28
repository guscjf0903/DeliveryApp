package com.example.deliveryapi.exception;

public class SalesFailedException extends RuntimeException{
    public SalesFailedException(String message) {
        super(message);
    }

}
