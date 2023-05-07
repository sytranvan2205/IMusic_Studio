package com.imusicstudio.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.imusicstudio.dto.AccountCreateDTO;

@Controller
public class HomeController {
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public ModelAndView getHomePage() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	@GetMapping("/login")
	public ModelAndView viewsLoginPage(AccountCreateDTO accountCreateDto, Model model) {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("accountCreateDto", new AccountCreateDTO());
		return mv;
	}
	@RequestMapping(value = { "/accessDenied" }, method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("404");
	}
}
