package com.bank.dto;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.bank.entity.Customer;

public class AccountTypesDto {

	private int id;

	@NotNull(message="Account type is required")
	@NotBlank(message="Account type is required")
	@Size(min = 5, max = 20, message = "minimum 5 characters and maximum 20 characters are needed")
	@Pattern(regexp = "[A-Za-z]{20}")
	private String accountTypeName;
	
	
	private int numberOfAccounts;

	@ManyToOne
	Customer customer;
}
