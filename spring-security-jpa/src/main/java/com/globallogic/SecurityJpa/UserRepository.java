package com.globallogic.SecurityJpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globallogic.SecurityJpa.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUserName(String userName);
}
