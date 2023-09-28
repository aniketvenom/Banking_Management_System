package com.bank.dto;

import java.time.LocalDate;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto extends UserDto {

	private LocalDate dateOfAccountCreation;

	@NotNull(message = "balance is required")
	private double balance;

	@NotNull(message = "Aadhar number is required")
	@NotBlank(message = "Aadhar number is required")
	@Size(min = 12, max = 12, message = "12 numbers are required")
	@Pattern(regexp = "[0-9]{12}", message = "Only numeric digits are allowed")
	private String aadharNumber;

	private String accountNumber;

	@NotNull(message = "Pan number is required")
	@NotBlank(message = "Pan number is required")
	@Size(min = 10, max = 10, message = "10 numbers are required")
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "format:(AAAAA0000A) , only uppercase alphabats are allowed")
	private String panNumber;

	@NotNull(message = "Account Type is required")
	@NotBlank(message = "Account type is required")
	@Size(min = 3, message = "At least 3 characters ar erequired")
	@Size(min = 3, message = "max 3 characters are allowed")
	private String accountType;

}
