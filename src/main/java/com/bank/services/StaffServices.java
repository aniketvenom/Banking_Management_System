package com.bank.services;

import java.util.List;

import com.bank.dto.StaffDto;
import com.bank.entity.Staff;

public interface StaffServices {

	// Creating abstract method to create staff account
	StaffDto createStaffAccount(Staff staff);
	
	// Creating abstract method to delete staff details of a staff member using Staff ID 
	void deleteStaffByStaffId(String staffId);

	// Creating abstract method to update staff details of a staff member using Staff ID 
	StaffDto updateStaffDetailsByStaffId(String staffId,Staff staff);
	
	// Creating abstract method to get details of all the staff members
	List<StaffDto> getAllStaffMembers();
	
	// Creating abstract method to details of staff member using staff Id
	StaffDto getStaffMemberByStaffId(String staffId);
	
	
}
