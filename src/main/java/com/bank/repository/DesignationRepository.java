package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.Designation;

public interface DesignationRepository extends JpaRepository<Designation, Integer> {

}
