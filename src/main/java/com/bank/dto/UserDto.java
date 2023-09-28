package com.bank.dto;

import java.time.LocalDate;

import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.bank.entity.Address;
import com.bank.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private int id;

	@NotNull(message = "Name is required")
	@NotBlank(message = "Name is required")
	@Size(min = 3, message = "name must require at least 3 characters")
	@Size(max = 20, message = "max 20 characters are allowed")
	private String name;

	@Size(max = 20, message = "max 20 characters are allowed")
	private String lastName;

	@NotNull(message = "email is required")
	@NotBlank(message = "email is required")
	@Size(max = 30, message = "max 30 characters are allowed")
	@Email
	private String email;

	@NotNull(message = "contct number is required")
	@NotBlank(message = "contact number is required")
	@Size(min = 10, max = 10, message = " contact bumber must contain 10 digits")
	@Pattern(regexp = "[6,7,8,9]{1}[0-9]{9}", message = "contact number must start from 6,7,8 or 9")
	private String contactNumber;

	@NotNull(message = "gender is required")
	@NotBlank(message = "gender is required")
	@Size(min = 4, message = "gender must require at least 4 characters")
	@Size(max = 10, message = "max 10 characters are allowed")
	private String gender;

	private LocalDate dateOfBirth;

	private String userId;

	private String password;
	
	private final String ifscCode="BOAA3112980";

	@OneToOne
	private Role role;

	@OneToOne
	private Address address;
}
