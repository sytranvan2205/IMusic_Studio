package com.imusicstudio.controller.user;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.imusicstudio.entities.Product;
import com.imusicstudio.entities.ShoppingCart;
import com.imusicstudio.entities.User;
import com.imusicstudio.security.MyUser;
import com.imusicstudio.service.IUserService;
import com.imusicstudio.service.ProductsService;
import com.imusicstudio.service.ShoppingCartServices;

@Controller
public class CartController {
    @Autowired
    private IUserService userServices;
    @Autowired
    private ShoppingCartServices shoppingCartServices;
    @Autowired
    private ProductsService productServices;

    @GetMapping("/shoping-cart")
    public String cart(Model model, Principal principal, HttpSession session,Authentication authentication){
        if (principal ==null){
            return "redirect:/login"; // hoặc chuyển hướng đến trang đăng nhập
    }
    String username= principal.getName();
    User user=userServices.findByUserName(username);
    if (user==null){
        throw new RuntimeException("không tìm thấy thông tin người dùng");
    }
    ShoppingCart shoppingCart=user.getShoppingCart();
    model.addAttribute("shoppingcart",shoppingCart);
    if (shoppingCart == null || shoppingCart.getCartItems().size() == 0){
        model.addAttribute("check", "Không có sản phẩm nào trong giỏ hàng");

    }
    session.setAttribute("totalItems",shoppingCart.getTotalItems());
    model.addAttribute("subTotal",shoppingCart.getTotalPrices());
	if(authentication !=null) {
		MyUser myUser = (MyUser) authentication.getPrincipal();
		model.addAttribute("myUser", myUser);
	}
    return "shoping-cart";
    }

@PostMapping("/add-to-cart")
    public String addItemToCart(
            @RequestParam("id") Long productId,
            @RequestParam(value = "quantity",required = false,defaultValue = "1") int quantity,
            Principal principal
        ,HttpServletRequest request){

        if (principal ==null){
            return "redirect:/login";
        }
    Product product= productServices.getProductById(productId);

    String username=principal.getName();
    User user=userServices.findByUserName(username);

    ShoppingCart cart= shoppingCartServices.addItemToCart(product,quantity,user);
        return "redirect:"+request.getHeader("Referer");
//    return "redirect:/";
    }

    @RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=update")
    public String updateCart(@RequestParam("quantity") int quantity,
                             @RequestParam("id") Long productId,
                             Model model,Principal principal){
        if (principal ==null){
            return "redirect:/login";
        }else {
            String username=principal.getName();
            User user=userServices.findByUserName(username);
            Product product=productServices.getProductById(productId);
            ShoppingCart cart=shoppingCartServices.updateItemIncart(product,quantity,user);

            model.addAttribute("shoppingcart",cart);
            return "redirect:/shoping-cart";
        }
    }

    @RequestMapping(value = "/update-cart",method = RequestMethod.POST,params = "action=delete")
    public String deleteItemFromCart(@RequestParam("id") Long productId,
                                     Model model,
                                     Principal principal){
        if (principal==null){
            return "redirect:/login";
        }else {
            String username= principal.getName();
            User user=userServices.findByUserName(username);
            Product product=productServices.getProductById(productId);
            ShoppingCart cart=shoppingCartServices.deleteItemFromCart(product,user);
            model.addAttribute("shoppingcart",cart);
            return "redirect:/shoping-cart";
        }
    }



}
