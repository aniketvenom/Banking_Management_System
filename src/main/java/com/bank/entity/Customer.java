package com.bank.entity;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class Customer extends User {

	@Column(nullable = false)
	private LocalDate dateOfAccountCreation;

	@Column(nullable = false)
	private double balance;

	@Column(length = 12, nullable = false, unique = true)
	private String aadharNumber;

	@Column(length = 12, nullable = false, unique = true)
	private String accountNumber;

	@Column(length = 10, nullable = false, unique = true)
	private String panNumber;

	@Column(length=30,nullable=false)
	private String accountType;

	@Builder
	public Customer(int id, String name, String lastName, String email, String contactNumber, String gender,
			LocalDate dateOfBirth, String userId, String password, Role role, Address address,
			LocalDate dateOfAccountCreation, double balance, String aadharNumber, String accountNumber,
			String panNumber,String accountType) {
		super(id, name, lastName, email, contactNumber, gender, dateOfBirth, userId, password, role, address);
		this.dateOfAccountCreation = dateOfAccountCreation;
		this.balance = balance;
		this.aadharNumber = aadharNumber;
		this.accountNumber = accountNumber;
		this.panNumber = panNumber;
		this.accountType = accountType;
	}

}
