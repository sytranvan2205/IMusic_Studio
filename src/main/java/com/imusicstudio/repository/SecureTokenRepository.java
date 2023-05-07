package com.imusicstudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imusicstudio.entities.SecureToken;

public interface SecureTokenRepository extends JpaRepository<SecureToken, Long>{
	SecureToken findByToken(final String token);
	Long removeByToken(String token);
}
