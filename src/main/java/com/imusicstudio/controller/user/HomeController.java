package com.imusicstudio.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imusicstudio.entities.Product;
import com.imusicstudio.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.imusicstudio.dto.AccountCreateDTO;
import com.imusicstudio.security.MyUser;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
	@Autowired
	ProductService productService;
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public ModelAndView getHomePage(Authentication authentication) {

		ModelAndView mv = new ModelAndView("index");
		if(authentication == null) {
			mv.addObject("myUser", null);
			return mv;
		}
		MyUser myUser = (MyUser) authentication.getPrincipal();
		List<Product> products = new ArrayList<>();
			products= productService.getAllProduct();
		for (Product product:
				products) {
			System.out.println(product.getProductName().toString());
		}
		mv.addObject("myUser", myUser);
		mv.addObject("products", products);
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
	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/home");
	}
}
