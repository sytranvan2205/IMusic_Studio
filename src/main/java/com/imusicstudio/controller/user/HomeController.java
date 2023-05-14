package com.imusicstudio.controller.user;

import com.imusicstudio.entities.ShoppingCart;
import com.imusicstudio.entities.User;
import com.imusicstudio.service.UserServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController {

	private UserServices userServices;
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public ModelAndView getHomePage(Model model, Principal principal, HttpSession session) {
		if (principal != null){
			session.setAttribute("username",principal.getName());

			User user=userServices.findByUserName(principal.getName());

			ShoppingCart cart=user.getShoppingCart();
			session.setAttribute("totalItems",cart.getTotalItems());

		}else {
			session.removeAttribute("username");
		}
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
	@GetMapping("/add-to-cart")
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
