package com.imusicstudio.controller.user;

import com.imusicstudio.entities.Product;
import com.imusicstudio.entities.ShoppingCart;
import com.imusicstudio.entities.User;
import com.imusicstudio.service.ProductServices;
import com.imusicstudio.service.ShoppingCartServices;
import com.imusicstudio.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@Controller
public class CartController {
    @Autowired
    private UserServices userServices;
    @Autowired
    private ShoppingCartServices shoppingCartServices;
    @Autowired
    private ProductServices productServices;

    @GetMapping("/shoping-cart")
    public String cart(Model model, Principal principal){
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
        return "shoping-cart";
    }

@PostMapping("/add-to-cart")
    public String addItemToCart(
            @RequestParam("id") long productId,
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
