package com.imusicstudio.controller.user;

import com.imusicstudio.entities.Category;
import com.imusicstudio.entities.Product;
import com.imusicstudio.service.serviceImpl.CategoryServiceImpl;
import com.imusicstudio.service.serviceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
@Controller
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CategoryServiceImpl categoryService;
    @GetMapping("/products")
    public String getAllProduct(@RequestParam(defaultValue = "1") int page, Model model) {
        Page<Product> productPage = productService.findAll(page);
        List<Category> categories = categoryService.getAllCategory();
        List<Product> productsNew = productService.get6NewsestProduct();
        productPage.stream().sorted();
        model.addAttribute("productsNew1", productsNew.subList(0,2));
        model.addAttribute("productsNew2", productsNew.subList(3,5));
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("url", "/products");
        model.addAttribute("categories", categories);
        return "shop-grid";
    }
    @GetMapping("/products/search")
    public String searchProducts(@RequestParam("name") String productName, @RequestParam(defaultValue = "1") int page, Model model) {
        Page<Product> products = productService.getNameProductFromSearch(productName, page);
        List<Category> categories = categoryService.getAllCategory();
        List<Product> productsNew = productService.get6NewsestProduct();
        model.addAttribute("productsNew1", productsNew.subList(0,2));
        model.addAttribute("productsNew2", productsNew.subList(3,5));
        model.addAttribute("categories", categories);
        model.addAttribute("products", products.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("key", productName);
        model.addAttribute("url", "/products/search");
        return "shop-grid";
    }
    @GetMapping("/products/{categoryId}")
    public String getAllProductByCategory(@RequestParam(defaultValue = "1") int page, @PathVariable("categoryId") Long categoryId, Model model) {
        Page<Product> productPage = productService.getProductsByCategory(categoryId, page);
        List<Category> categories = categoryService.getAllCategory();
        List<Product> productsNew = productService.get6NewsestProduct();
        model.addAttribute("productsNew1", productsNew.subList(0,2));
        model.addAttribute("productsNew2", productsNew.subList(3,5));
        model.addAttribute("categories", categories);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("url", "/products/"+categoryId);
        return "shop-grid";
    }
}
