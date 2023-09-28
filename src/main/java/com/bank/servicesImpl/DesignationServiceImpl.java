package com.bank.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.StaffDto;

import com.bank.entity.Designation;
import com.bank.entity.Staff;
import com.bank.exceptions.ResourceNotFoundException;
import com.bank.repository.DesignationRepository;
import com.bank.repository.StaffRepository;
import com.bank.services.DesignationServices;
import com.bank.util.StaffConverter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DesignationServiceImpl implements DesignationServices {

	// Logger created
	public static final Logger log = org.slf4j.LoggerFactory.getLogger(Designation.class);

	@Autowired
	StaffRepository staffRepository;

	@Autowired
	DesignationRepository desRepository;

	@Autowired
	StaffConverter staffConverter;

	// overriding the abstract method assign Designation To Staff using account type and
    // defining its function
	// assigning designation to staff using this method
	@Override
	public void assignDesignationToStaff(String staffId, int designationId) {

		Staff staff = staffRepository.findByStaffId(staffId)
				.orElseThrow(() -> new ResourceNotFoundException("Staff Member ", "Staff Id ", staffId));

		Designation des = desRepository.findById(designationId)
				.orElseThrow(() -> new ResourceNotFoundException("Designation ", "Designation Id ", designationId));

		staff.setDesignation(des);

		staffRepository.save(staff);

	}
	// overriding the abstract method  get Staff By DesignationId and
    // defining its function
	// getting staff details by designation ID using this method
    @Override
	public List<StaffDto> getStaffByDesignationId(int desId) {

		Designation des = desRepository.findById(desId)
				.orElseThrow(() -> new ResourceNotFoundException("Designation ", "Designation Id ", desId));

		List<Staff> staffList = staffRepository.findByDesignation(des);

		List<StaffDto> staffDtoList = new ArrayList<StaffDto>();

		if (staffList != null) {
			for (Staff staff : staffList) {
				staffDtoList.add(staffConverter.staffToStaffDtoConverter(staff));
			}
		}

		return staffDtoList;
	}

	// overriding the abstract method update Designation Of StaffMember and
    // defining its function
	// Updating designation of staff member using this method
	@Override
	public void updateDesignationOfStaffMember(String staffId, int desId) {

		Staff staff = staffRepository.findByStaffId(staffId)
				.orElseThrow(() -> new ResourceNotFoundException("Staff Member ", "Staff Id ", staffId));

		Designation des = desRepository.findById(desId)
				.orElseThrow(() -> new ResourceNotFoundException("Designation ", "Designation Id ", desId));

		staff.setDesignation(null);

		staff.setDesignation(des);

		staffRepository.save(staff);

	}
	// overriding the abstract method get All Designation Name and
    // defining its function
	// Getting name of all the designations using this method

	@Override
	public List<Designation> getAllDesignationName() {
		
		
		List<Designation> desList = desRepository.findAll();
		return desList;
	}

}
