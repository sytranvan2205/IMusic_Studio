package com.imusicstudio.service;

import com.imusicstudio.entities.SecureToken;
import com.imusicstudio.entities.User;

public interface ISecureTokenService {
	SecureToken createSecureToken(User user);

	void saveSecureToken(SecureToken token);

	SecureToken findByToken(String token);

	void removeToken(SecureToken token);
	
	void removeTokenByToken(String token);
}
