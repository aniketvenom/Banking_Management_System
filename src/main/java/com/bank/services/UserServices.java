package com.bank.services;

import com.bank.entity.User;

public interface UserServices {

	User login(String userName,String password);
	
}
