package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.RecycleBin;
import com.bank.services.RecycleBinServices;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class RecycleBinController {

	@Autowired
	RecycleBinServices rbService;
	
	@GetMapping("/getAllDeletedAccounts")
	public List<RecycleBin> ListgetAllDeletedAccounts()
	{
		return rbService.getAllAccounter();
	}
}
