package com.imusicstudio.controller.user;

import com.imusicstudio.entities.Product;
import com.imusicstudio.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Base64;

@Controller
public class ProductController {
    @Autowired
    private ProductServices productServices;

    @GetMapping("/shop-details/{id}")
    public String getProductById(@PathVariable long id, Model model) {
        Product product = productServices.getProductById(id);
        if (product ==null) {
            model.addAttribute("errorMessage","Sản phẩm không tồn tại");
//        return '404';
        }
        model.addAttribute("product", product);
        return "shop-details";
    }


}

