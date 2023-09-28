package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.bank.config.JwtFilter;

@SpringBootApplication
public class BankingManagementSystemApplication {

//	@Bean
//	public FilterRegistrationBean jwtFilter()
//	{
//		FilterRegistrationBean register = new FilterRegistrationBean();
//		register.setFilter(new JwtFilter());
//		register.addUrlPatterns("/bank/*");
//		return register;
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(BankingManagementSystemApplication.class, args);
	}

}
