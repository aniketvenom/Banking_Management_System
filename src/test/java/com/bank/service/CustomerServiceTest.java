package com.bank.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bank.entity.Address;
import com.bank.entity.Customer;
import com.bank.repository.CustomerRepository;
import com.bank.services.CustomerServices;
import com.bank.util.CustomerConverter;

@SpringBootTest
class CustomerServiceTest {

	@Autowired
	CustomerServices customerServices;

	@MockBean
	CustomerRepository customerRepository;

	@Autowired
	CustomerConverter customerConverter;

	// POSITIVE TEST CASE TO GET CUSTOMER ACCOUNT BY ADHAAR NUMBER
	@Test
	void testGetCustomerAccountByAadharNumber() {
		Address address = Address.builder().city("Solan").pincode("173211").build();
		Customer customer = Customer.builder().name("ram").email("ram2@gmail.com").gender("male")
				.contactNumber("9805269142").dateOfBirth(LocalDate.of(2002, 12, 30)).accountNumber(" 49819652852")
				.dateOfAccountCreation(LocalDate.now()).balance(45000).aadharNumber("223486482852").panNumber(" FDUPA7965K")
				.address(address).build();

		Mockito.when(customerRepository.findByAadharNumber(customer.getAadharNumber()))
				.thenReturn(Optional.of(customer));

		assertThat(customerServices.getCustomerAccountByAdhaarNumber(customer.getAadharNumber()).getName())
				.isEqualTo(customer.getName());
	}

	// POSITIVE TEST CASE TO GET CUSTOMER ACCOUNT BY ID
	@Test
	void testGetCustomerAccountById() {
		Address address = Address.builder().city("Solan").pincode("173211").build();
		Customer customer = Customer.builder().name("ram").email("ram2@gmail.com").gender("male")
				.contactNumber("9805269142").dateOfBirth(LocalDate.of(2002, 12, 30)).accountNumber(" 49819652852")
				.dateOfAccountCreation(LocalDate.now()).aadharNumber(" 223486482852").panNumber(" FDUPA7965K")
				.address(address).build();

		Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

		assertThat(customerServices.getCustomerAccountById(customer.getId()).getName()).isEqualTo("ram");

	}

	// POSITIVE TEST CASE TO CHECK WHEATHER THE CUSTOMER'S ACCOUNT IS BEING CREAETED
	// OR NOT
	@Test
	void testCreateCustomerAccount() {
		Address address = Address.builder().city("Solan").pincode("245101").build();
		Customer customer = Customer.builder().name("ram").email("ram2@gmail.com").gender("male")
				.contactNumber("9805269142").dateOfBirth(LocalDate.of(2002, 12, 30)).accountNumber(" 49819652852")
				.dateOfAccountCreation(LocalDate.now()).balance(45000).aadharNumber(" 223486482852").panNumber(" FDUPA7965K")
				.address(address).build();

		Mockito.when(customerRepository.save(customer)).thenReturn(customer);

		assertEquals(customer.getName(), customerServices.createCustomerAccount(customer).getName());

	}

	// Negative TEST CASE TO GET CUSTOMER ACCOUNT BY ID
	@Test
	@DisplayName("Negative Test Case")
	void testNegativeGetCustomerAccountById() {
		Address address = Address.builder().city("Solan").pincode("173213").build();
		Customer customer = Customer.builder().name("ram").email("ram2@gmail.com").gender("male")
				.contactNumber("9805269142").dateOfBirth(LocalDate.of(2002, 12, 30)).accountNumber(" 49819652852")
				.dateOfAccountCreation(LocalDate.now()).aadharNumber(" 223486482852").panNumber(" FDUPA7965K")
				.address(address).build();

		Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

		assertThat(customerServices.getCustomerAccountById(customer.getId()).getEmail()).isEqualTo("ram123@gmail.com");

	}

	// NEGATIVE TEST CASE TO GET CUSTOMER ACCOUNT BY ADHAAR NUMBER
	@Test
	@DisplayName("Negative Test Case")
	void testNegativeGetCustomerAccountByaadharNumber() {
		Address address = Address.builder().city("Solan").pincode("173213").build();
		Customer customer = Customer.builder().name("ram").email("ram2@gmail.com").gender("male")
				.contactNumber("9805269142").dateOfBirth(LocalDate.of(2002, 12, 30)).accountNumber(" 49819652852")
				.dateOfAccountCreation(LocalDate.now()).aadharNumber("223486482852").panNumber(" FDUPA7965K")
				.address(address).build();

		Mockito.when(customerRepository.findByAadharNumber(customer.getAadharNumber()))
				.thenReturn(Optional.of(customer));

		assertThat(customerServices.getCustomerAccountByAdhaarNumber(customer.getAadharNumber()).getName())
				.isEqualTo("Aniket");

		

	}

}
