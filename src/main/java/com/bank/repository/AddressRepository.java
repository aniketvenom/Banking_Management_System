package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
