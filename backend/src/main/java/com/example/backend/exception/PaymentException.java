package com.example.backend.exception;

public class PaymentException extends Exception {

    // Constructor that accepts a message
    public PaymentException(String message) {
        super(message);
    }

    // Optionally, you can add a constructor that accepts both a message and a cause
    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }
}