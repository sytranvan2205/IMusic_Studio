package com.imusicstudio.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.imusicstudio.dto.AccountCreateDTO;
import com.imusicstudio.entities.Category;
import com.imusicstudio.entities.Product;
import com.imusicstudio.security.MyUser;
import com.imusicstudio.service.CategoryService;
import com.imusicstudio.service.ProductsService;

@Controller
public class HomeController {
	@Autowired
	ProductsService productService;
	@Autowired
	CategoryService categoryService;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)

	public ModelAndView getHomePage(Authentication authentication) {

		ModelAndView mv = new ModelAndView("index");
		List<Category> categories = categoryService.getAllCategory();
		mv.addObject("categories", categories);
		if (authentication == null) {
			mv.addObject("myUser", null);
			List<Product> products = productService.getAllProduct();
//			List<Category> categories = categoryService.getAllCategory();
			mv.addObject("categories", categories);
			System.out.println("-----");
			mv.addObject("products", products);
			return mv;
		}
		MyUser myUser = (MyUser) authentication.getPrincipal();
		List<Product> products = productService.getAllProduct();
//		List<Category> categories = categoryService.getAllCategory();
		mv.addObject("categories", categories);
		mv.addObject("products", products);
		mv.addObject("myUser", myUser);
		return mv;

	}

	@GetMapping("/home/category/{id}")
	public ModelAndView productByCat(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("index");
		List<Product> products = productService.getAllProductByCategoryId(id);
		List<Category> categories = categoryService.getAllCategory();
		mv.addObject("categories", categories);
		mv.addObject("products", products);
		return mv;
	} // view Products By Category

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
	public String viewsShopGridPage(Model model,Authentication authentication) {
		if(authentication !=null) {
			MyUser myUser = (MyUser) authentication.getPrincipal();
			model.addAttribute("myUser", myUser);
		}
		return "shop-grid";
	}

	@GetMapping("/shop-details")
	public String viewsShopDetailsPage(Model model,Authentication authentication) {
		if(authentication !=null) {
			MyUser myUser = (MyUser) authentication.getPrincipal();
			model.addAttribute("myUser", myUser);
		}
		return "shop-details";
	}

//	@GetMapping("/shoping-cart")
	@GetMapping("/add-to-cart")
	public String viewsShopingCartPage() {
		return "shoping-cart";
	}

	@GetMapping("/contact")
	public String viewsContactPage(Model model,Authentication authentication) {
		if(authentication !=null) {
			MyUser myUser = (MyUser) authentication.getPrincipal();
			model.addAttribute("myUser", myUser);
		}
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
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/home");
	}

}
