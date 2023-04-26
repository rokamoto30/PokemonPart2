package com.cognixia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(InvalidException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) // will add documentation for 404 error we get when exception is thrown
	public ResponseEntity<?> invalidException(InvalidException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
//	@ExceptionHandler(ConstraintViolationException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND) // will add documentation for 404 error we get when exception is thrown
//	public ResponseEntity<?> constraintViolationException(ConstraintViolationException ex, WebRequest request) {
//		return new ResponseEntity<>("A field is not valid * Go on. Guess ;) *", HttpStatus.NOT_FOUND);
//	}
}
