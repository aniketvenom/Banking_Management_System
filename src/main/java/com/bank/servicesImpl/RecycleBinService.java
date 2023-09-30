package com.bank.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entity.RecycleBin;
import com.bank.repository.RecycleBinRepository;
import com.bank.services.RecycleBinServices;

@Service
public class RecycleBinService implements RecycleBinServices {

	@Autowired
	RecycleBinRepository rbRepository;

	@Override
	public List<RecycleBin> getAllAccounter() {

		return rbRepository.findAll();
	}

}
