package com.imusicstudio.service;

import java.util.List;

import com.imusicstudio.entities.Role;

public interface IRoleService {
	Role createRole(Role role);
	List<Role> findAll();
	Role findRoleByCode(String code);
	Role findRoleById(Long id);
}
