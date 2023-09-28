package com.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.entity.Designation;
import com.bank.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

	// Query to find staff member using the staff ID
	@Query("From Staff where staffId=:staffId")
	Optional<Staff> findByStaffId(@Param("staffId") String staffId);

	// To find staff member using his email or contact number
	Staff findByEmailOrContactNumber(String email, String contactNumber);

	// To find staff member using his designation
	List<Staff> findByDesignation(Designation des);

}
