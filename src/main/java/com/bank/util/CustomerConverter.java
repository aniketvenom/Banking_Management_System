package com.bank.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.bank.dto.CustomerDto;
import com.bank.entity.Customer;
import com.bank.entity.RecycleBin;

@Component
public class CustomerConverter {

	//Converting customer entity to customerDto
	public CustomerDto CustomerToCustomerDtoConverter(Customer customer) {
		CustomerDto customerDto = new CustomerDto();

		if (customer != null) {
			BeanUtils.copyProperties(customer, customerDto);
		}

		return customerDto;

	}
	
	//converting customerDto to customer entity
	public Customer customerDtoToCustomerCoverter(CustomerDto customerDto)
	{
		Customer customer = new Customer();
		
		if(customerDto != null)
		{
			BeanUtils.copyProperties(customerDto, customer);
		}
		
		return customer;
	}
	
	//converting customer to recycleBin entity
	public RecycleBin customerToRecycleBinConveter(Customer customer)
	{
		RecycleBin recycleBin = new RecycleBin();
		
		BeanUtils.copyProperties(customer, recycleBin);
		
		return recycleBin;
	}

}
