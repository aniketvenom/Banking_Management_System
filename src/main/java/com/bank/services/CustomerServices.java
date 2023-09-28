package com.bank.services;

import java.util.List;

import com.bank.dto.CustomerDto;

import com.bank.entity.Customer;
import com.bank.entity.TransactionDetails;
import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.ResourceNotFoundException;

import com.bank.exceptions.WithdrawAndDepositLimitException;

public interface CustomerServices {

	// creating abstract method to create new account of the customer in the bank
	CustomerDto createCustomerAccount(Customer customer);

	// creating abstract method to fetch the details of all the customer present in
	// the bank
	List<CustomerDto> getCustomersAccounts();

	// creating abstract method to fetch the details of single customer by his id
	CustomerDto getCustomerAccountById(int customerId);

	// creating abstract method to fetch customer details by his adhaar number
	CustomerDto getCustomerAccountByAdhaarNumber(String customerAdharNumber);

	// creating abstract method to remove the customer details from the bank
	void deleteAnyCustomerUsingAccountNumber(String accountNumber);

	// creating abstract method to update customer's account using adhaar number
	CustomerDto updateCustomerDetailsByAdhaarNumber(String customerAdharNumber, Customer customer);

	// creating abstract method to deposit money into customer's account using his account number
	void deposit(String accountNumber, double amount)
			throws WithdrawAndDepositLimitException, IllegalArgumentException, ResourceNotFoundException;

	
	// creating abstract method to withdraw money from customer's account using his account number
	void withdraw(String accountNumber, double amount) throws WithdrawAndDepositLimitException, IllegalArgumentException,
			InsufficientFundsException, ResourceNotFoundException;

	// creating abstract method to get customer's transaction details using his account number
	List<TransactionDetails> getCustomerTransactionDetailsUsingAccountNumber(String accountNumber);
	
	double checkBalance(String accountNumber);
	
	List<CustomerDto> getAllCustomerUsingAccountType(String accountType);
	
	CustomerDto getCustomerByAccountNumber(String accountNumber);

}
