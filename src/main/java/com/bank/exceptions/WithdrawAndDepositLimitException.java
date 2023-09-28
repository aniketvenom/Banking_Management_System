package com.bank.exceptions;

public class WithdrawAndDepositLimitException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WithdrawAndDepositLimitException(String message)
	{
		super(message);
	}
}
