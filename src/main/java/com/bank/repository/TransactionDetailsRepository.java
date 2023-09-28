package com.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.entity.TransactionDetails;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Integer> {

	// query to get transaction details of a customer using his account number
	@Query("From TransactionDetails where Customer=(From Customer where accountNumber=:accountNumber)")
	List<TransactionDetails> getCustomerTransactionDetailsByCustomerAccountNumber(@Param("accountNumber") String accountNumber);

	// query to get transaction details of a customer using his customer Id
	@Query("From TransactionDetails where Customer=(From Customer where id=:i)")
	List<TransactionDetails> getCustomerTransactionDetailsByCustomerId(@Param("i") int id);
	
	List<TransactionDetails> findByCustomerId(int id);
}
