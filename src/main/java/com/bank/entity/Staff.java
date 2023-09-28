package com.bank.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import javax.persistence.OneToOne;

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
@Inheritance(strategy = InheritanceType.JOINED)
public class Staff extends User {

	@Column(nullable = false)
	private double salary;

	@Column(nullable = false)
	private LocalDate dateOfJoining;
	
	@Column(nullable=false,unique=true,length=10)
	private String staffId;

	@OneToOne
	Designation designation;
	
	@Builder
	public Staff(int id, String name, String lastName, String email, String contactNumber, String gender,
			LocalDate dateOfBirth, String userId, String password, Role role, Address address,
			double salary, LocalDate dateOfJoining, String staffId, Designation designation ) {
		super(id, name, lastName, email, contactNumber, gender, dateOfBirth, userId, password, role, address);
		this.salary = salary;
		this.dateOfJoining = dateOfJoining;
		this.staffId = staffId;
		this.designation = designation;
		
	}
}
