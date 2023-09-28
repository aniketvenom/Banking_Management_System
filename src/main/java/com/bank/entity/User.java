package com.bank.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(length = 20, nullable = false)
	private String name;

	@Column(length = 20)
	private String lastName;

	@Column(length = 30, nullable = false, unique = true)
	private String email;

	@Column(length = 10, nullable = false, unique = true)
	private String contactNumber;

	@Column(length = 10, nullable = false)
	private String gender;

	@Column(nullable = false)
	private LocalDate dateOfBirth;

	@Column(length = 20, nullable = false, unique = true)
	private String userId;
	
	@Column(length = 8, nullable = false)
	private String password;
	
	@Column(length=11, nullable=false)
	private final String ifscCode="BOAA3112980";

	@OneToOne
	private Role role;
	
	@OneToOne
	private Address address;

}
