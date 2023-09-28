package com.bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.StaffDto;
import com.bank.services.StaffServices;
import com.bank.util.StaffConverter;

@RestController
public class StaffController {

	@Autowired
	StaffServices staffService;
	
	@Autowired
	StaffConverter staffConverter;
	
	// To create Staff members account
	@PostMapping("/createStaffAccount")
	public StaffDto createStaffAccount(@Valid @RequestBody StaffDto staffDto)
	{
		return staffService.createStaffAccount(staffConverter.staffDtoToStaffConverter(staffDto));
	}
	
	// To update details of staff members using StaffId
	@PostMapping("/updateStaffMemberDetailsUsingStaffId/{staffId}")
	public StaffDto updateStaffMemberDetailsUsingStaffId(@PathVariable("staffId") String staffId,@Valid @RequestBody StaffDto staff)
	{
		return staffService.updateStaffDetailsByStaffId(staffId, staffConverter.staffDtoToStaffConverter(staff));
	}
	
	// To get details of all the staff members
	@GetMapping("/getAllStaffMembers")
	public List<StaffDto> getAllStaffMembers()
	{
		return staffService.getAllStaffMembers(); 
	}
	
	// To get details of staff memebers using their Id
	@GetMapping("/getStaffMemberUsingStaffId/{staffId}")
	public StaffDto getStaffMemberDetailsUsingStaffId(@PathVariable("staffId") String staffId)
	{
		return staffService.getStaffMemberByStaffId(staffId);
	}
	
	// TO delete details of staff members using staff Id
	@DeleteMapping("/deleteStaffMemberByStaffId/{staffId}")
	public void deleteStaffByStaffId(@PathVariable("staffId") String staffId)
	{
		staffService.deleteStaffByStaffId(staffId);
	}
}


