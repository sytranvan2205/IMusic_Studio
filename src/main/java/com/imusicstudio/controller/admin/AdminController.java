package com.imusicstudio.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	@GetMapping("/testadmin")
	public String viewsAdminPage() {
		return "admin/adminindex";
	}

}
