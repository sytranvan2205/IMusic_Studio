package com.imusicstudio.service;

import com.imusicstudio.dto.AccountCreateDTO;
import com.imusicstudio.entities.User;

public interface IUserService {
	User createUser(AccountCreateDTO accountCreateDTO);
	void verifyCode(String token);
	User saveInfor(User user);
	User findByUserName(String username);
}
