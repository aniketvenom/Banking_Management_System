package com.bank.exceptions;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsufficientFundsException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientFundsException(String message) {
		super("Insufficient Funds");

  }
 }

