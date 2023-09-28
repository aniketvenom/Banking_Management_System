package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.StaffDto;
import com.bank.entity.Designation;
import com.bank.services.DesignationServices;

@RestController
public class DesignationController {

	@Autowired
	DesignationServices desServices;

	// To assign designation to a staff memeber using staff Id
	@PostMapping("/assignStaffMemberToItsDesignationWithStaffId/{staffId}/DesignationId/{desId}")
	public ResponseEntity<?> assignStaffMemberToItsDesignation(@PathVariable("staffId") String staffId,@PathVariable("desId") int desId)
	{
		
		desServices.assignDesignationToStaff(staffId, desId);
		
		return new ResponseEntity<String>("Staff Member with staff Id "+staffId+" has been assigned to its designation with designation id "+desId,HttpStatus.OK);
	
	}
	
	// To get staff members using their designation 
	@GetMapping("/getStaffByTheirDesignation/{desId}") 
	public List<StaffDto> getStaffMembersAccordingToTheirDesignation(@PathVariable("desId") int desId)
	{
		return desServices.getStaffByDesignationId(desId);
	}
	
	// To update the designation of a staff member using his Id
	@PostMapping("/updateStaffDesignationWithStaffId/{staffId}/designationId/{desId}")
	public ResponseEntity<?> updateDesignation(@PathVariable("staffId") String staffId,@PathVariable("desId")  int desId)
	{
		desServices.updateDesignationOfStaffMember(staffId, desId);
		
		return new ResponseEntity<String>("Staff Member's Designation updated ",HttpStatus.OK);
	}
	
	// To get all designations 
	@GetMapping("/getAllDesignations")
	public List<Designation> getAllDesignation()
	{
		return desServices.getAllDesignationName();
	}
}
