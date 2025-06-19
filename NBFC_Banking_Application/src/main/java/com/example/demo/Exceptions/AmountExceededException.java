package com.example.demo.Exceptions;

public class AmountExceededException extends Exception {
    public AmountExceededException(String message) {
        super(message);
    }
}
