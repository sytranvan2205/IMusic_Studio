package com.imusicstudio.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.imusicstudio.constant.SystemConstant;
import com.imusicstudio.entities.Role;
import com.imusicstudio.entities.User;
import com.imusicstudio.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}
		boolean enable = userEntity.isAccountVerified();
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : userEntity.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getCode()));
		}
		MyUser user = new MyUser(userEntity.getUserName(), userEntity.getPassword(), enable, true, true, true, authorities);
		user.setFullName(userEntity.getFullName());
		return user;
	}

}
