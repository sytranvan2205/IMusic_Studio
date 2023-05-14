package com.imusicstudio.controller;

import com.imusicstudio.entities.Product;
import com.imusicstudio.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/shop-details/{id}")
    public String getProductById(@PathVariable long id, Model model) {
        Product product = productService.getProductById(id);
        if (product ==null) {
            model.addAttribute("errorMessage","Sản phẩm không tồn tại");
        return "404";
        }
        model.addAttribute("product", product);
        return "shop-details";
    }


}

