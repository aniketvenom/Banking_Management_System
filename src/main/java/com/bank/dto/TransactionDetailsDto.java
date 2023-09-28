package com.bank.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.OneToOne;

import com.bank.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionDetailsDto {

	private int transactionId;

	private double amount;

	private LocalDate dateOfTransaction;

	private LocalTime timeOfTransaction;

	private String transactionType;

	@OneToOne
	Customer customer;
}
