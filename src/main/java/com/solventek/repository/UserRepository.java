package com.solventek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solventek.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
