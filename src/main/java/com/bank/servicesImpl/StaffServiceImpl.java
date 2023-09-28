package com.bank.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bank.dto.StaffDto;
import com.bank.entity.Address;
import com.bank.entity.RecycleBin;
import com.bank.entity.Role;
import com.bank.entity.Staff;
import com.bank.exceptions.ResourceNotFoundException;
import com.bank.repository.AddressRepository;
import com.bank.repository.RecycleBinRepository;
import com.bank.repository.RoleRepository;
import com.bank.repository.StaffRepository;
import com.bank.services.StaffServices;
import com.bank.util.StaffConverter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StaffServiceImpl implements StaffServices {

	// Logger created
	public static final Logger log = org.slf4j.LoggerFactory.getLogger(Staff.class);

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	StaffRepository staffRepository;

	@Autowired
	StaffConverter staffConverter;

	@Autowired
	RecycleBinRepository recyclebinRepository;

	// overriding the abstract method  create Staff Account and
    // defining its function
	//creating staff account using this method
	@Override
	public StaffDto createStaffAccount(Staff staff) {

		Staff existedStaff = staffRepository.findByEmailOrContactNumber(staff.getEmail(), staff.getContactNumber());

		if (existedStaff != null) {
			throw new DataIntegrityViolationException("Customer with  email " + staff.getEmail() + "or phone number "
					+ staff.getContactNumber() + " already exists. Customer's name is " + existedStaff.getName());
		}

		if (staff.getSalary() <= 0) {
			throw new IllegalArgumentException("Salary must be grater than 0 !");
		}

		staff.setUserId(staff.getEmail().substring(0, staff.getEmail().indexOf('@')));

		staff.setPassword(staff.getName().substring(0, 3) + "123");

		staff.setStaffId("S" + "0" + staff.getContactNumber().substring(3, 10));

		Role role = roleRepository.findById(2).get();
		staff.setRole(role);

		staff.setAddress(staff.getAddress());
		addressRepository.save(staff.getAddress());

		staffRepository.save(staff);

		return staffConverter.staffToStaffDtoConverter(staff);
	}
	
	// overriding the abstract method  update Staff Details By StaffId and
    // defining its function
	//Updating staff details using this method
	@Override
	public StaffDto updateStaffDetailsByStaffId(String staffId, Staff staff) {

		Staff existingStaff = staffRepository.findByStaffId(staffId)
				.orElseThrow(() -> new ResourceNotFoundException("Staff Member", "Staff Id", staffId));

		existingStaff.setContactNumber(staff.getContactNumber());
		existingStaff.setDateOfBirth(staff.getDateOfBirth());
		existingStaff.setDateOfJoining(staff.getDateOfJoining());
		existingStaff.setEmail(staff.getEmail());
		existingStaff.setGender(staff.getGender());
		existingStaff.setLastName(staff.getLastName());
		existingStaff.setName(staff.getName());
		existingStaff.setSalary(staff.getSalary());
		Address existingAddress = existingStaff.getAddress();

		if (staff.getAddress() != null) {

			existingAddress.setCity(staff.getAddress().getCity());
			existingAddress.setPincode(staff.getAddress().getPincode());
			existingAddress.setState(staff.getAddress().getState());
			addressRepository.save(existingAddress);
		}

		
		staffRepository.save(existingStaff);
		return staffConverter.staffToStaffDtoConverter(existingStaff);
	}
	
	// overriding the abstract method get All Staff Members and
    // defining its function
	// getting details of all the staff members using this method
    @Override
	public List<StaffDto> getAllStaffMembers() {

		List<Staff> staffList = staffRepository.findAll();
		List<StaffDto> staffDtoList = new ArrayList<StaffDto>();

		for (Staff staffMember : staffList) {
			staffDtoList.add(staffConverter.staffToStaffDtoConverter(staffMember));
		}

		return staffDtoList;
	}

    // overriding the abstract method get Staff Member By StaffId and
    // defining its function
	// getting details of  the staff members by Id using this method
	@Override
	public StaffDto getStaffMemberByStaffId(String staffId) {

		return staffConverter.staffToStaffDtoConverter(staffRepository.findByStaffId(staffId)
				.orElseThrow(() -> new ResourceNotFoundException("Staff Member", "Staff Id", staffId)));
	}

	// overriding the abstract method delete Staff Member By StaffId and
    // defining its function
	// deleting details of  the staff members by Id using this method
	@Override
	public void deleteStaffByStaffId(String staffId) {

		Staff staff = staffRepository.findByStaffId(staffId)
				.orElseThrow(() -> new ResourceNotFoundException("Staff", "staffId", staffId));

		staff.setDesignation(null);
		
		staff.setRole(null);
		
		int addressId = staff.getAddress().getAId();
		
		staff.setAddress(null);
		
		addressRepository.deleteById(addressId);

		recyclebinRepository.save(staffConverter.staffToRecycleBinConveter(staff));

		staffRepository.delete(staff);

	}

}
