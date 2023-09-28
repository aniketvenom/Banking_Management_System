package com.bank.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.User;
import com.bank.services.UserServices;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServices userServices;

	// To login using UserId and Password
	@PostMapping("/login/{userId}/{password}")
	public String login(@PathVariable("userId") String userId, @PathVariable("password") String password)
			throws ServletException {
		String jwtToken = "";

		if (userId.isBlank() || password.isBlank()) {
			throw new ServletException("Please enter correct user id and password");
		}

		User user = userServices.login(userId, password);

		if (user == null) {
			throw new ServletException("User not found");
		}

		return jwtToken = Jwts.builder().setSubject(user.getUserId()).claim("Role", user.getRole().getRoleName())
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() * 7 * 24 * 60 * 60))
				.signWith(SignatureAlgorithm.HS256, "venom").compact();

	}
}
