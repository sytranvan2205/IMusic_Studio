package com.imusicstudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imusicstudio.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findOneByUserNameAndStatus(String username, int status);
	User findUserByUserName(String username);
	
}
