package com.bank.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignationDto {

	private int dId;

	@NotBlank(message = "designation name is required")
	@NotNull(message = "designation name is required")
	@Size(min = 2, message = "at least 2 characters are required")
	@Size(max = 20, message = "maximum 20 characters are allowed")
	private String designationName;

}
