package com.bank.exceptions;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> ResourceNotFoundException(ResourceNotFoundException ex) {
		ErrorDetails error = new ErrorDetails(new Date(), ex.getMessage());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(InsufficientFundsException.class)
	public ResponseEntity<?> InsufficientFundsException(InsufficientFundsException ex) {

		ErrorDetails error = new ErrorDetails(new Date(), ex.getMessage());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(WithdrawAndDepositLimitException.class)
	public ResponseEntity<?> WithdrawAndDepositLimitException(WithdrawAndDepositLimitException ex) {
		ErrorDetails error = new ErrorDetails(new Date(), ex.getMessage());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> IllegalArgumentException(IllegalArgumentException ex) {
		ErrorDetails error = new ErrorDetails(new Date(), ex.getMessage());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ServletException.class)
	public ResponseEntity<?> servletExceptionHandler(ServletException se) {
		ErrorDetails error = new ErrorDetails(new Date(), se.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
		ErrorDetails error = new ErrorDetails(new Date(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
