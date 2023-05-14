package com.imusicstudio.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

//	@GetMapping("/admin")
//	public String viewsAdminPage() {
//		return "admin/fragments-Admin";
//	}
}
