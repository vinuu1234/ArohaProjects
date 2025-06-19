package com.example.demo.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
//Customer exceptions
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> handleCustomerRetrievalException(CustomerNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleDataIntegrityException(DataIntegrityViolationException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Database constraint violation: " + ex.getMessage());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
		String message = "HTTP method not supported: ";
		return new ResponseEntity<>(message, HttpStatus.METHOD_NOT_ALLOWED);
	}

	// Loan Product and Loan Application Exceptions
	@ExceptionHandler(LoanProductNotFoundException.class)
	public ResponseEntity<String> handleLoanProductNotFoundException(LoanProductNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidLoanTypeException.class)
	public ResponseEntity<String> handleInvalidLoanTypeException(InvalidLoanTypeException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);// 400
	}

	@ExceptionHandler(AmountExceededException.class)
	public ResponseEntity<String> handleAmountExceededException(IllegalArgumentException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);// 400
	}

	@ExceptionHandler(ApplicationNotFoundException.class)
	public ResponseEntity<String> handleApplicationNotFoundException(ApplicationNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);// 404

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);// 404

	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<String> handleBusinessException(BusinessException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);// 404

	}

	@ExceptionHandler(LoanAlreadyExistsException.class)
	public ResponseEntity<String> handleLoanAlreadyExistsException(LoanAlreadyExistsException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Database constraint violation: " + ex.getMessage());
	}
	
	@ExceptionHandler(PaymentNotCompletedException.class)
	public ResponseEntity<String> handlePaymentNotCompletedException(PaymentNotCompletedException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);// 404

	}

}
