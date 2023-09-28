package com.bank.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bank.dto.StaffDto;
import com.bank.entity.Address;
import com.bank.entity.Staff;
import com.bank.repository.StaffRepository;
import com.bank.services.StaffServices;

@SpringBootTest

class StaffServiceTest {

    @Autowired
    StaffServices staffServices;

    @MockBean
    private StaffRepository staffRepository;


    // Positive Test case to get staff member by Id
    @Test
    void testGetStaffMemberById() {
        Address address = Address.builder().city("Solan").pincode("173211").build();
        Staff staff = Staff.builder().name("Aniket").email("aniket@gmail.com").gender("male")
                .contactNumber("9805269142").dateOfBirth(LocalDate.of(1980, 1, 15)).salary(45000).dateOfJoining(LocalDate.of(2022,5, 10)).
                 staffId("1")
                .address(address).build();

        // Mock the behavior of the staffRepository
        Mockito.when(staffRepository.findByStaffId(staff.getStaffId())).thenReturn(Optional.of(staff));

        // Test the staffServices method
        assertThat(staffServices.getStaffMemberByStaffId(staff.getStaffId()).getName()).isEqualTo("Aniket");
    
        
    
    }
    
    // Positive Test case to create Staff account  
    @Test
    void testCreateStaffAccount() {
        Address address = Address.builder().city("Solan").pincode("173211").build();
        Staff staffToCreate = Staff.builder().name("Aditi").email("aditi@gmail.com").gender("female")
                .contactNumber("9876543210").dateOfBirth(LocalDate.of(1990, 5, 20)).salary(60000)
                .address(address).build();

        // Mock the behavior of staffRepository.save to return the staff object
        Mockito.when(staffRepository.save(staffToCreate)).thenReturn(staffToCreate);

        // Test the staffServices method
        StaffDto createdStaff = staffServices.createStaffAccount(staffToCreate);

        // Assert that the created staff has the expected values
        assertThat(createdStaff.getName()).isEqualTo("Aditi");
        assertThat(createdStaff.getEmail()).isEqualTo("aditi@gmail.com");
        assertThat(createdStaff.getGender()).isEqualTo("female");
        assertThat(createdStaff.getContactNumber()).isEqualTo("9876543210");
        assertThat(createdStaff.getDateOfBirth()).isEqualTo(LocalDate.of(1990, 5, 20));
        assertThat(createdStaff.getSalary()).isEqualTo(60000);
        assertThat(createdStaff.getAddress()).isEqualTo(address);
    }
    
    // Negative Test case to create staff account
    @Test
    void negativeTestCreateStaffAccount() {
        Address address = Address.builder().city("Solan").pincode("173211").build();
        Staff staffToCreate = Staff.builder().name("Aditi").email("aditi@gmail.com").gender("female")
                .contactNumber("9876543210").dateOfBirth(LocalDate.of(1990, 5, 20)).salary(60000)
                .address(address).build();

        // Mock the behavior of staffRepository.save to return the staff object
        Mockito.when(staffRepository.save(staffToCreate)).thenReturn(staffToCreate);

        // Test the staffServices method
        StaffDto createdStaff = staffServices.createStaffAccount(staffToCreate);

        // Assert that the created staff has the expected values
        assertThat(createdStaff.getName()).isEqualTo("Avantika");
        assertThat(createdStaff.getEmail()).isEqualTo("aditi@gmail.com");
        assertThat(createdStaff.getGender()).isEqualTo("female");
        assertThat(createdStaff.getContactNumber()).isEqualTo("9876543210");
        assertThat(createdStaff.getDateOfBirth()).isEqualTo(LocalDate.of(1990, 5, 20));
        assertThat(createdStaff.getSalary()).isEqualTo(60000);
        assertThat(createdStaff.getAddress()).isEqualTo(address);
    
   }
    
    // Negative Test case to get Staff member by Id
    @Test
    void negativeTestGetStaffMemberById() {
        Address address = Address.builder().city("Solan").pincode("173211").build();
        Staff staff = Staff.builder().name("Aniket").email("aniket@gmail.com").gender("male")
                .contactNumber("9805269142").dateOfBirth(LocalDate.of(1980, 1, 15)).salary(45000).dateOfJoining(LocalDate.of(2022,5, 10)).
                 staffId("1")
                .address(address).build();

        // Mock the behavior of the staffRepository
        Mockito.when(staffRepository.findByStaffId(staff.getStaffId())).thenReturn(Optional.of(staff));

        // Test the staffServices method
        assertThat(staffServices.getStaffMemberByStaffId(staff.getStaffId()).getEmail()).isEqualTo("rohan@gmail.com");
    
}
}





   

  



