package com.bank.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RecycleBin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 20)
	private String name;

	@Column(length = 20)
	private String lastName;

	@Column(length = 30)
	private String email;

	@Column(length = 10)
	private String contactNumber;

	@Column(length = 10)
	private String gender;

	@Column()
	private LocalDate dateOfBirth;

	@Column(length = 20)
	private String userId;

	@Column(length = 8)
	private String password;

	@Column()
	private LocalDate dateOfAccountCreation;

	@Column()
	private double balance;

	@Column(length = 12)
	private String aadharNumber;

	@Column(length = 12)
	private String accountNumber;

	@Column(length = 10)
	private String panNumber;
}
