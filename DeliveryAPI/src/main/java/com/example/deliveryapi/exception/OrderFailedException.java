package com.example.deliveryapi.exception;

public class OrderFailedException extends RuntimeException{
    public OrderFailedException(String message) {
        super(message);
    }
}
