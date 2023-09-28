package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
