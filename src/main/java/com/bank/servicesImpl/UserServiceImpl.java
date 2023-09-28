package com.bank.servicesImpl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.bank.entity.User;
import com.bank.repository.UserRepository;
import com.bank.services.UserServices;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserServices {

	// Logger created
		public static final Logger log = org.slf4j.LoggerFactory.getLogger(User.class);
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User login(String userName, String password) {

		return userRepository.findByUserIdAndPassword(userName, password);
	}

}
