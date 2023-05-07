package com.imusicstudio.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.imusicstudio.dto.AccountCreateDTO;
import com.imusicstudio.dto.VerifyCodeDTO;
import com.imusicstudio.entities.SecureToken;
import com.imusicstudio.entities.User;
import com.imusicstudio.service.IUserService;

@Controller
public class UserController {
	@Autowired
	private IUserService userService;
	
	@PostMapping("sign-up")
	public String signUp(AccountCreateDTO accountCreateDto, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			return "sign-up";
		}

		User account = userService.createUser(accountCreateDto);
		accountCreateDto.setId(account.getId());

		return "redirect:/verify-code";
	}
	
	@GetMapping("verify-code")
	public String verifyCode(Model model) {
		model.addAttribute("verifyCode", new SecureToken());
		return "verify-code";
	}
	
	@PostMapping("verify-code")
	public String verifyCodeAction(Model model, VerifyCodeDTO verifyCodeDto, BindingResult result) {
		if(result.hasErrors()) {
			return "verify-code";
		}
		userService.verifyCode(verifyCodeDto.getToken());
		return "redirect:/login";
	}
}
