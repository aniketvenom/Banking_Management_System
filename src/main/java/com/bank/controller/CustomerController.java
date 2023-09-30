package com.bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.CustomerDto;

import com.bank.entity.TransactionDetails;
import com.bank.exceptions.ResourceNotFoundException;
import com.bank.exceptions.WithdrawAndDepositLimitException;
import com.bank.services.CustomerServices;
import com.bank.util.CustomerConverter;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class CustomerController {

	@Autowired
	CustomerServices customerServices;

	@Autowired
	CustomerConverter customerConverter;

	// To create new customer account
	@PostMapping("/createNewCustomerAccount")
	public CustomerDto createCustomerNewAccount(@Valid @RequestBody CustomerDto customerDto) {
		return customerServices.createCustomerAccount(customerConverter.customerDtoToCustomerCoverter(customerDto));

	}

	// TO get all customer accounts
	@GetMapping("/getCustomersAccounts")
	public List<CustomerDto> getCustomersAllAccounts() {
		return customerServices.getCustomersAccounts();
	}

	// To get customer account using Id
	@GetMapping("/getCustomerAccountById/{customerId}")
	public CustomerDto getCustomerAccountByUd(@PathVariable("customerId") int customerId) {
		return customerServices.getCustomerAccountById(customerId);
	}

	// To get customer account By Adhaar Number
	@GetMapping("/getCustomerAccountByAdhaarNumber/{customerAdharNumber}")
	public CustomerDto getCustomeAccountByAdharNumber(@PathVariable("customerAdharNumber") String customerAdharNumber) {
		return customerServices.getCustomerAccountByAdhaarNumber(customerAdharNumber);
	}

	// To delete customer account by account number
	@DeleteMapping("/deleteCustomerAccountByAccountNumber/{accountNumber}")
	public void deleteCustomerAccountByAccountNumber(
			@PathVariable("accountNumber") String customerAccountNumber) {
		customerServices.deleteAnyCustomerUsingAccountNumber(customerAccountNumber);
//		return new ResponseEntity<String>("customer with Account Number " + customerAccountNumber + " has been deleted",
//				HttpStatus.OK);
	}

	// To update customer details using adhaar number
	@PostMapping("/updateCustomerUsingaccountNumber/{accountNumber}")
	public CustomerDto updateCustomerDetailsUsingAdharNumber(@PathVariable("accountNumber") String accountNumber,
			@Valid @RequestBody CustomerDto customer) {
		return customerServices.updateCustomerDetailsByAccountNumber(accountNumber,
				customerConverter.customerDtoToCustomerCoverter(customer));
	}

	// To deposit any amount into customer's account account using account number
	@PostMapping("/depositIntoCustomerAccountWithAccountNumber/{accountNumber}/amount/{amount}")
	public void deposteIntoCustomerAccount(@PathVariable("accountNumber") String accountNumber,
			@PathVariable("amount") double amount)
			throws IllegalArgumentException, ResourceNotFoundException, WithdrawAndDepositLimitException {
		customerServices.deposit(accountNumber, amount);

//		return new ResponseEntity<String>(
//				amount + " has been deposited in customer's account with account number " + accountNumber,
//				HttpStatus.OK);
	}

	// To withdraw any amount from customer's account account using account number
	@PostMapping("/withdrawFromCustomerAccountWithAccountNumber/{accountNumber}/amount/{amount}")
	public void withdrawFromCustomerAccount(@PathVariable("accountNumber") String accountNumber,
			@PathVariable("amount") double amount)
			throws IllegalArgumentException, ResourceNotFoundException, WithdrawAndDepositLimitException {
		customerServices.withdraw(accountNumber, amount);

//		return new ResponseEntity<String>(amount + " has been deducted from your account ", HttpStatus.OK);
	}

	// To get customer's transactiona details using his account number
	@GetMapping("/getCustomerTransactionDetailsUsingAccountNumber/{accountNumber}")
	public List<TransactionDetails> getCustomerTransactionDetailsUsingAccountNumber(
			@PathVariable("accountNumber") String accountNumber) {
		return customerServices.getCustomerTransactionDetailsUsingAccountNumber(accountNumber);
	}

	// To check balance using account number
	@GetMapping("/checkBalance/{accountNumber}")
	public double checkBalanceByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
		return customerServices.checkBalance(accountNumber);
	}

	// To get customers with same account type
	@GetMapping("/getCustomersWithAccountTypeName/{accountTypeName}")
	public List<CustomerDto> getCustomerWithAccountTypeName(@PathVariable("accountTypeName") String accountTypeName)
	{
		return customerServices.getAllCustomerUsingAccountType(accountTypeName);
	}
	
	// To get customers using account number
	@GetMapping("/getCustomerByAccountNumber/{accountNumber}")
	public CustomerDto getCustomerByAccountNumber(@PathVariable("accountNumber") String accountNumber)
	{
		return customerServices.getCustomerByAccountNumber(accountNumber);
	}
}
