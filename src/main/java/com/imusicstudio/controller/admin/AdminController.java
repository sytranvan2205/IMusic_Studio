package com.imusicstudio.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	@RequestMapping(value = { "/admin"}, method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mv = new ModelAndView("admin/home");
		return mv;
	}

}
