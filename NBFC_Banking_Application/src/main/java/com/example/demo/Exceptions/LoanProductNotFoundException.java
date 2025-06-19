package com.example.demo.Exceptions;

public class LoanProductNotFoundException extends RuntimeException {
    public LoanProductNotFoundException(String message) {
        super(message);
    }
}

