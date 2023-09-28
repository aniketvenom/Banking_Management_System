package com.bank.dto;

import java.time.LocalDate;

import javax.persistence.OneToOne;

import javax.validation.constraints.NotNull;

import com.bank.entity.Designation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffDto extends UserDto {

	@NotNull(message = "Salary is required")
	private double salary;
	
	@NotNull(message = "Date of joining is required")
	private LocalDate dateOfJoining;
	
	private String staffId;
	
	@OneToOne
	Designation designation;
}


