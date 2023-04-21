package com.imusicstudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imusicstudio.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findOneByUserNameAndStatus(String username, int status);
	
}
