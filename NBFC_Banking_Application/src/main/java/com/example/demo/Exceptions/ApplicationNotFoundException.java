package com.example.demo.Exceptions;

public class ApplicationNotFoundException extends RuntimeException {

	public ApplicationNotFoundException(String message) {
		super(message);
	}
}
