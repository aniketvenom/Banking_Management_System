package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserIdAndPassword(String userId,String password);
}
