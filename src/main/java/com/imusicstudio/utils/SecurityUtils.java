package com.imusicstudio.utils;

import java.util.ArrayList;
import java.util.List;

import com.imusicstudio.security.MyUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityUtils {
	public static List<String> getAuthorities(){
		List<String> result = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for(GrantedAuthority authority : authorities) {
			result.add(authority.getAuthority());
		}
		return result;
	}
	public static MyUser getPrincipal() {
		MyUser myUser = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return myUser;
	}
}
