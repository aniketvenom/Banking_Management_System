package com.bank.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.bank.dto.StaffDto;

import com.bank.entity.RecycleBin;
import com.bank.entity.Staff;

@Component
public class StaffConverter {

	public StaffDto staffToStaffDtoConverter(Staff staff) {
		StaffDto staffDto = new StaffDto();
		if (staff != null) {
			BeanUtils.copyProperties(staff, staffDto);
		}

		return staffDto;
	}

	public Staff staffDtoToStaffConverter(StaffDto staffDto) {
		Staff staff = new Staff();
		if (staffDto != null) {
			BeanUtils.copyProperties(staffDto, staff);
		}

		return staff;
	}

	// converting customer to recycleBin entity
	public RecycleBin staffToRecycleBinConveter(Staff staff) {
		RecycleBin recycleBin = new RecycleBin();

		BeanUtils.copyProperties(staff, recycleBin);

		return recycleBin;
	}
}
