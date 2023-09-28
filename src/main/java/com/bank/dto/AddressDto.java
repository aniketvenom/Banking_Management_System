package com.bank.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

	private int aId;

	@NotNull(message = "city name is required")
	@Size(min = 3, message = "city name must require 3 characters")
	@Size(max = 10, message = "max 10 characters are allowed")
	@NotBlank(message = "city name is required")
	private String city;

	@NotNull(message = "pincode is required")
	@Size(min = 6, max = 6, message = "6 numbers are required")
	@NotBlank(message = "pincode is required")
	@Pattern(regexp = "[0-9]{6}")
	private String pincode;

	@NotNull(message = "state name is required")
	@Size(min = 6, message = "at least 3 characters must be there")
	@Size(max = 30, message = "not more than 30 characters are allowed")
	@NotBlank(message = "state name is required")
	private String state;
}
