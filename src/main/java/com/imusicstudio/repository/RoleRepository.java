package com.imusicstudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imusicstudio.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findRoleByCode(String code);
}
