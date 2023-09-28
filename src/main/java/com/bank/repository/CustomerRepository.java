package com.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	// customer method inside customer repository
	// To find customer using aadhar number
	Optional<Customer> findByAadharNumber(String adharnumber);
	
    // To find customer using his account number
	Optional<Customer> findByAccountNumber(String accountNumber);
	
	// Query to delete customer using his account number
	@Query("Delete From Customer where accountNumber=:an")
	void deleteByAccountNumber(@Param("an") String accountNumber);

	// To find customer using his email or contact number
	Customer findByEmailOrContactNumber(String email,String contact);
	
	// To find customer using his aadhar number or pan number
	Customer findByAadharNumberOrPanNumber(String adharNumber,String panNumber);

	// To find customer using the account type
	Optional<List<Customer>> findByAccountType(String accountType);
	

}
