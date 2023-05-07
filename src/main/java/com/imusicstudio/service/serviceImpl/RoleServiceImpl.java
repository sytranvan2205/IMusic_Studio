package com.imusicstudio.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imusicstudio.entities.Role;
import com.imusicstudio.repository.RoleRepository;
import com.imusicstudio.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{
	@Autowired
	private RoleRepository roleRepo;

	@Override
	public Role createRole(Role role) {
		roleRepo.save(role);
		return role;
	}

	@Override
	public List<Role> findAll() {
		List<Role> roles = roleRepo.findAll();
		return roles;
	}

	@Override
	public Role findRoleByCode(String code) {
		return roleRepo.findRoleByCode(code);
	}

	@Override
	public Role findRoleById(Long id) {
		return roleRepo.findById(id).get();
	}

}
