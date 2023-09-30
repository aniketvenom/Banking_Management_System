package com.bank.servicesImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bank.dto.CustomerDto;

import com.bank.entity.Address;
import com.bank.entity.Customer;

import com.bank.entity.Role;
import com.bank.entity.TransactionDetails;
import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.ResourceNotFoundException;
import com.bank.exceptions.WithdrawAndDepositLimitException;

import com.bank.repository.AddressRepository;
import com.bank.repository.CustomerRepository;
import com.bank.repository.RecycleBinRepository;
import com.bank.repository.RoleRepository;
import com.bank.repository.TransactionDetailsRepository;
import com.bank.services.CustomerServices;
import com.bank.util.CustomerConverter;
import com.bank.util.TransactionDetailsConverter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServisesImplementation implements CustomerServices {

	// Logger created
	public static final Logger log = org.slf4j.LoggerFactory.getLogger(Customer.class);

	// creating the object of CustomerRepository class to call the function of
	// CustomerRespository
	@Autowired
	CustomerRepository customerRepository;

	// creating the object of AddressRepository class to call the function of this
	// class
	@Autowired
	AddressRepository addressRepository;

	// creating the object of RoleRepository class to call the function of this
	// class
	@Autowired
	RoleRepository roleRepository;

	// creating the object of CustomerConverter class to call the function of this
	// class
	@Autowired
	CustomerConverter customerConverter;

	// creating the object of RecycleBinRepository class to call the function of
	// this
	// class
	@Autowired
	RecycleBinRepository recycleBinRepository;

	@Autowired
	TransactionDetailsRepository tnxnRepository;

	@Autowired
	TransactionDetailsConverter txnDetailsConverter;

	// overriding the abstract method createCustomerAccount and defining its
	// function
	@Override
	public CustomerDto createCustomerAccount(Customer customer) {

		Customer existedCustomer = customerRepository.findByEmailOrContactNumber(customer.getEmail(),
				customer.getContactNumber());

		if (existedCustomer != null) {
			throw new DataIntegrityViolationException("Customer with  email " + customer.getEmail() + "or phone number "
					+ customer.getContactNumber() + " already exists. Customer's name is " + existedCustomer.getName());
		}

		Customer existedCustomer2 = customerRepository.findByAadharNumberOrPanNumber(customer.getAadharNumber(),
				customer.getPanNumber());

		if (existedCustomer2 != null) {
			throw new DataIntegrityViolationException("Customer with Aadhar Number " + customer.getAadharNumber()
					+ "or Pan number " + customer.getPanNumber() + " already exists. Customer's name is "
					+ existedCustomer2.getName());
		}

		if (customer.getBalance() <= 0) {
			throw new IllegalArgumentException("Balance must be grater than 0 !");
		}

		// setting user id of the customer to be its email id before @
		customer.setUserId(customer.getEmail().substring(0, customer.getEmail().indexOf('@')));

		// setting the password of the customer to be first 3 letters of his name and
		// adding 123 also it will be in a lower case
		customer.setPassword(customer.getName().substring(0, 3).toLowerCase() + "123");

		// setting the date of account creation
		LocalDate currentDate = LocalDate.now();
		customer.setDateOfAccountCreation(currentDate);

		// setting the account number
		customer.setAccountNumber(
				"4981" + customer.getPanNumber().substring(6, 9) + customer.getAadharNumber().substring(8, 12));

		// setting the role
		Role role = roleRepository.findById(3).get();
		customer.setRole(role);

		// setting the address
		Address add = customer.getAddress();
		customer.setAddress(add);
		addressRepository.save(add);

		// saving customer details
		customerRepository.save(customer);

		return customerConverter.CustomerToCustomerDtoConverter(customer);

	}

	// overriding the abstract method getCustomersAccounts and defining its function
	@Override
	public List<CustomerDto> getCustomersAccounts() {

		List<Customer> customerList = customerRepository.findAll();
		List<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();

		for (Customer customer : customerList) {
			customerDtoList.add(customerConverter.CustomerToCustomerDtoConverter(customer));
		}

		return customerDtoList;
	}

	// overriding the abstract method getCustomerAccountById and defining its
	// function
	@Override
	public CustomerDto getCustomerAccountById(int customerId) {

		return customerConverter.CustomerToCustomerDtoConverter(customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId)));
	}

	// overriding the abstract method getCustomerAccountById and defining its
	// function
	@Override
	public CustomerDto getCustomerAccountByAdhaarNumber(String customerAdharNumber) {

		return customerConverter
				.CustomerToCustomerDtoConverter(customerRepository.findByAadharNumber(customerAdharNumber).orElseThrow(
						() -> new ResourceNotFoundException("Customer", "Adhar Number", customerAdharNumber)));
	}

	// overriding the abstract method getCustomerAccountById and defining its
	// function
	// deleting customer's account from customer entity and moving it into the
	// recycle bin entity
	@Override
	public void deleteAnyCustomerUsingAccountNumber(String accountNumber) {

		Customer customer = customerRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "accountNumber", accountNumber));

		List<TransactionDetails> transactionDetails = tnxnRepository.findByCustomerId(customer.getId());
				

		if (transactionDetails != null) {
			for (TransactionDetails txn : transactionDetails) {
				txn.setCustomer(null);
				tnxnRepository.delete(txn);
			}

		}

		customer.setRole(null);

		int addressId = customer.getAddress().getAId();
		customer.setAddress(null);
		addressRepository.deleteById(addressId);

		recycleBinRepository.save(customerConverter.customerToRecycleBinConveter(customer));
		customerRepository.delete(customer);

	}

	
	

	// overriding the abstract method deposit and defining its function
	// depositing an amount into customer's account using this method
	@Override
	public void deposit(String accountNumber, double amount)
			throws WithdrawAndDepositLimitException, ResourceNotFoundException, IllegalArgumentException {

		Customer customer = customerRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Account Number", accountNumber));

		if (amount <= 0) {
			throw new IllegalArgumentException("Invalid deposit amount. Amount must be greater than 0.");
		}

		if (amount < 100) {
			throw new WithdrawAndDepositLimitException("You need at least 100rs. to perform deposite in your account");
		}

		double newBalance = customer.getBalance() + amount;

		TransactionDetails txnDetails = new TransactionDetails();
		txnDetails.setAmount(amount);
		txnDetails.setDateOfTransaction(LocalDate.now());
		txnDetails.setTimeOfTransaction(LocalTime.now());
		txnDetails.setTransactionType("Deposite");

		txnDetails.setCustomer(customer);

		tnxnRepository.save(txnDetails);

		customer.setBalance(newBalance);

		customerRepository.save(customer);
	}

	// overriding the abstract method withdraw and defining its function
	// withdrawing an amount from customer's account using this method
	@Override
	public void withdraw(String accountNumber, double amount) throws WithdrawAndDepositLimitException,
			IllegalArgumentException, InsufficientFundsException, ResourceNotFoundException {
		Customer customer = customerRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Account Number", accountNumber));

		if (amount <= 0) {
			throw new IllegalArgumentException("Invalid withdrawal amount. Amount must be greater than 0.");
		}

		if (customer.getBalance() < amount) {
			throw new InsufficientFundsException("Insufficient funds.");
		}

		if (amount < 100) {
			throw new WithdrawAndDepositLimitException("You need at least 100rs. to perform withdraw in your account");
		}

		customer.setBalance(customer.getBalance() - amount);

		TransactionDetails txnDetails = new TransactionDetails();
		txnDetails.setAmount(amount);
		txnDetails.setDateOfTransaction(LocalDate.now());
		txnDetails.setTimeOfTransaction(LocalTime.now());
		txnDetails.setTransactionType("Withdraw");

		txnDetails.setCustomer(customer);

		tnxnRepository.save(txnDetails);

		customerRepository.save(customer);

	}

	// overriding the abstract method  get Customer Transaction Details Using AccountNumber and 
    //defining its function
   // Getting transaction details of customer using this method
	@Override
	public List<TransactionDetails> getCustomerTransactionDetailsUsingAccountNumber(String accountNumber) {

		Customer customer = customerRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber", accountNumber));

		List<TransactionDetails> transactionList = tnxnRepository.findByCustomerId(customer.getId());

		return transactionList;

	}

	// overriding the abstract method check balance and defining its function
   // checking balance of customer using this method
	@Override
	public double checkBalance(String accountNumber) {

		Customer customer = customerRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber", accountNumber));

		return customer.getBalance();
	}

	// overriding the abstract method get all customers using account type and
	// defining its function
	// getting details of all customers with same account type using this method
	@Override
	public List<CustomerDto> getAllCustomerUsingAccountType(String accountType) {

		List<Customer> customerList = customerRepository.findByAccountType(accountType)
				.orElseThrow(() -> new ResourceNotFoundException("Accounts", "Account Type", accountType));

		List<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();

		for (Customer customer : customerList) {
			customerDtoList.add(customerConverter.CustomerToCustomerDtoConverter(customer));
		}

		return customerDtoList;
	}

	 // overriding the abstract method get customers using account number and
    // defining its function
   // getting details of customers by account number using this method
	@Override
	public CustomerDto getCustomerByAccountNumber(String accountNumber) {

		Customer customer = customerRepository.findByAccountNumber(accountNumber)
				.orElseThrow((() -> new ResourceNotFoundException("Account", "AccountNumber", accountNumber)));

		return customerConverter.CustomerToCustomerDtoConverter(customer);
	}

	
	// overriding the abstract method updateCustomerDetailsByAdhaarNumber and
		// defining its function
		// updating customer's details using this method
	@Override
	public CustomerDto updateCustomerDetailsByAccountNumber(String accountNumber, Customer customer) {
		
		Customer existingCustomer = customerRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Account number", accountNumber));

		existingCustomer.setContactNumber(customer.getContactNumber());
		existingCustomer.setDateOfBirth(customer.getDateOfBirth());
		existingCustomer.setGender(customer.getGender());
		existingCustomer.setName(customer.getName());
		existingCustomer.setLastName(customer.getLastName());
		
		existingCustomer.setEmail(customer.getEmail());
		
		existingCustomer.setAadharNumber(customer.getAadharNumber());
		
		existingCustomer.setPanNumber(customer.getPanNumber());
		
		existingCustomer.setBalance(customer.getBalance());
		
		if(customer.getAddress() != null)
		{
			existingCustomer.getAddress().setCity(customer.getAddress().getCity());
			existingCustomer.getAddress().setState(customer.getAddress().getState());
			existingCustomer.getAddress().setPincode(customer.getAddress().getPincode());
			
			addressRepository.save(existingCustomer.getAddress());
		}

		
		customerRepository.save(existingCustomer);

		return customerConverter.CustomerToCustomerDtoConverter(existingCustomer);
	}

}
