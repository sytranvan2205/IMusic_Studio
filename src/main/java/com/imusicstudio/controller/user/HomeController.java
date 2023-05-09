package com.imusicstudio.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public ModelAndView getHomePage() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	@GetMapping("/login")
	public String viewsLoginPage() {
		return "login";
	}
	@RequestMapping(value = { "/accessDenied" }, method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("404");
	}
	@GetMapping("/shop-grid")
	public String viewsShopGridPage() {
		return "shop-grid";
	}
	@GetMapping("/shop-details")
	public String viewsShopDetailsPage() {
		return "shop-details";
	}
	@GetMapping("/shoping-cart")
	public String viewsShopingCartPage() {
		return "shoping-cart";
	}
	@GetMapping("/contact")
	public String viewsContactPage() {
		return "contact";
	}
	@GetMapping("/checkout")
	public String viewsCheckoutPage() {
		return "checkout";
	}
	@GetMapping("/blog")
	public String viewsBlogPage() {
		return "blog";
	}
}
