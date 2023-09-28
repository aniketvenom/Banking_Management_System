package com.bank.services;

import java.util.List;

import com.bank.dto.StaffDto;
import com.bank.entity.Designation;

public interface DesignationServices {

	// Creating abstract method to assign designation to a staff member
	void assignDesignationToStaff(String staffId, int designationId);

    // Creating abstract method to get staff details using Designation Id
	List<StaffDto> getStaffByDesignationId(int desId);
	
	// Creating abstract method to update designation of a staff member 
	void updateDesignationOfStaffMember(String staffId,int desId);
	
	// Creating abstract method to get all the designations 
	List<Designation> getAllDesignationName();
}
