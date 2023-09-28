package com.bank.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;

	@Column(nullable = false)
	private double amount;

	@Column(nullable = false)
	private LocalDate dateOfTransaction;

	@Column(nullable = false)
	private LocalTime timeOfTransaction;

	@Column(nullable = false)
	private String transactionType;
	
	@OneToOne
	Customer customer;

}
