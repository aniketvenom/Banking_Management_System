package com.bank.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String resourceName;
	private String fieldName;
	private Object fieldObject;
	
	public ResourceNotFoundException(String resourceName,String fieldName,Object fieldObject)
	{
		super(String.format("%s not found with %s: '%s'",resourceName,fieldName,fieldObject));
		
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.fieldObject=fieldObject;
	}

}
