package com.imusicstudio.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.imusicstudio.dto.AccountCreateDTO;
import com.imusicstudio.entities.Role;
import com.imusicstudio.entities.SecureToken;
import com.imusicstudio.entities.User;
import com.imusicstudio.mail.Mail;
import com.imusicstudio.mail.MailService;
import com.imusicstudio.repository.UserRepository;
import com.imusicstudio.service.IRoleService;
import com.imusicstudio.service.ISecureTokenService;
import com.imusicstudio.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private ISecureTokenService secureTokenService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private MailService mailService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User createUser(AccountCreateDTO accountCreateDTO) {
		String fullName = accountCreateDTO.getFullName();
		String userName = accountCreateDTO.getUserName();
		String email = accountCreateDTO.getEmail();
		String password = accountCreateDTO.getPassword();

		Role role = roleService.findRoleByCode("USER");
		List<Role> roles = new ArrayList<>();
		roles.add(role);

		User user = new User();
		user.setUserName(userName);
		user.setUserEmail(email);
		user.setFullName(fullName);
		user.setPassword(passwordEncoder.encode(password));
		user.setAccountVerified(false);
		user.setRoles(roles);
		user.setStatus(1);
		
		User userSaved = userRepository.save(user);
		SecureToken token = secureTokenService.createSecureToken(userSaved);
		Map<String, Object> maps = new HashMap<>();
		maps.put("user", user);
		maps.put("token", token.getToken());

		Mail mail = new Mail();
		mail.setFrom("imusicstudio4nvb@gmail.com");
		mail.setSubject("Registration");
		mail.setTo(user.getUserEmail());
		mail.setModel(maps);
		try {
			mailService.sendEmail(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return userSaved;
	}

	public User update(User user) {
		return userRepository.save(user);
	}

	public void verifyCode(String token) {

		SecureToken verifyAccount = secureTokenService.findByToken(token);
		User account = verifyAccount.getUser();
		account.setAccountVerified(true);
		update(account);
	}
	
    @Override
    public User saveInfor(User user) {
        return null;
    }

	@Override
    public User findByUserName(String username) {
        return userRepository.findUserByUserName(username) ;

    }
}
