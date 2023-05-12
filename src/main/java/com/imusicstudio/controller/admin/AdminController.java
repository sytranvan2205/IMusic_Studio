package com.imusicstudio.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdminController {
	@GetMapping("/admin")
	public String viewsAdminPage() {
		return "admin/adminindex";
	}

	
//	@GetMapping("/Admin")
//	public String viewsAdminPage() {
//		return "admin/fragments-Admin";
//	}



}
