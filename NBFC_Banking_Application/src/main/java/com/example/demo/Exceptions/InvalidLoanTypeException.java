package com.example.demo.Exceptions;

public class InvalidLoanTypeException extends RuntimeException {
    public InvalidLoanTypeException(String message) {
        super(message);
    }
}