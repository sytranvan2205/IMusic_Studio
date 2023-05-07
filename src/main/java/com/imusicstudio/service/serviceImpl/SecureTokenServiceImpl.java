package com.imusicstudio.service.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.imusicstudio.entities.SecureToken;
import com.imusicstudio.entities.User;
import com.imusicstudio.repository.SecureTokenRepository;
import com.imusicstudio.service.ISecureTokenService;
import com.imusicstudio.utils.RandomUtil;

@Service
public class SecureTokenServiceImpl implements ISecureTokenService {
	@Value("${jdj.secure.token.validity}")
	private int tokenValidityInSeconds;
	

	
	@Autowired
	private SecureTokenRepository secureTokenRepository;

	@Override
	public SecureToken createSecureToken(User user) {
		String tokenValue = RandomUtil.generateRandomStringNumber(6).toUpperCase();
		SecureToken secureToken = new SecureToken();
		secureToken.setToken(tokenValue);
		secureToken.setExpireAt(LocalDateTime.now().plusSeconds(getTokenValidityInSeconds()));
		secureToken.setUser(user);
		this.saveSecureToken(secureToken);
		return secureToken;
	}

	@Override
	public void saveSecureToken(SecureToken token) {
		secureTokenRepository.save(token);
	}

	public int getTokenValidityInSeconds() {
		return tokenValidityInSeconds;
	}

	@Override
	public void removeToken(SecureToken token) {
		secureTokenRepository.delete(token);
	}

	@Override
	public void removeTokenByToken(String token) {
		secureTokenRepository.removeByToken(token);
	}

	@Override
	public SecureToken findByToken(String token) {
		return secureTokenRepository.findByToken(token);
	}

}
