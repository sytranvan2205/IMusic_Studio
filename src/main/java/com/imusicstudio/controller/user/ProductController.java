package com.imusicstudio.controller.user;

import com.imusicstudio.entities.Category;
import com.imusicstudio.entities.Product;
import com.imusicstudio.service.serviceImpl.CategoryServiceImpl;
import com.imusicstudio.service.serviceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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

	    Sort sort;
	    @GetMapping("/products")
	    public String getAllProduct(@RequestParam(defaultValue = "1") int page,@RequestParam(value = "sort", defaultValue = "name_asc") String sortOption , Model model) {
	        switch (sortOption){
	            case "name_asc":
	                sort = Sort.by("productName").ascending();
	                break;
	            case "name_desc":
	                sort = Sort.by("productName").descending();
	                break;
	            case "price_asc":
	                sort = Sort.by("productPrice").ascending();
	                break;
	            case "price_desc":
	                sort = Sort.by("productPrice").descending();
	                break;
	        }
	        Page<Product> productPage = productService.findAll(page, sort);
	        List<Category> categories = categoryService.getAllCategory();
	        List<Product> productsNew = productService.get6NewsestProduct();
	        model.addAttribute("productsNew1", productsNew.subList(0,2));
	        model.addAttribute("productsNew2", productsNew.subList(3,5));
	        model.addAttribute("products", productPage.getContent());
	        model.addAttribute("currentPage", page);
	        model.addAttribute("totalPages", productPage.getTotalPages());
	        model.addAttribute("url", "/products");
	        model.addAttribute("categories", categories);
	        model.addAttribute("sort_option", sortOption);
	        return "shop-grid";
	    }
	    @GetMapping("/products/search")
	    public String searchProducts(@RequestParam("name") String productName,@RequestParam(value = "sort", defaultValue = "name_asc") String sortOption , @RequestParam(defaultValue = "1") int page, Model model) {
	        switch (sortOption){
	            case "name_asc":
	                sort = Sort.by("productName").ascending();
	                break;
	            case "name_desc":
	                sort = Sort.by("productName").descending();
	                break;
	            case "price_asc":
	                sort = Sort.by("productPrice").ascending();
	                break;
	            case "price_desc":
	                sort = Sort.by("productPrice").descending();
	                break;
	        }
	        Page<Product> products = productService.getNameProductFromSearch(productName, page, sort);
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
	        model.addAttribute("sort_option", sortOption);
	        return "shop-grid";
	    }
	    @GetMapping("/products/{categoryId}")
	    public String getAllProductByCategory(@RequestParam(defaultValue = "1") int page ,@RequestParam(value = "sort", defaultValue = "name_asc") String sortOption, @PathVariable("categoryId") Long categoryId, Model model) {
	        switch (sortOption){
	            case "name_asc":
	                sort = Sort.by("productName").ascending();
	                break;
	            case "name_desc":
	                sort = Sort.by("productName").descending();
	                break;
	            case "price_asc":
	                sort = Sort.by("productPrice").ascending();
	                break;
	            case "price_desc":
	                sort = Sort.by("productPrice").descending();
	                break;
	        }
	        Page<Product> productPage = productService.getProductsByCategory(categoryId, page, sort);
	        List<Category> categories = categoryService.getAllCategory();
	        List<Product> productsNew = productService.get6NewsestProduct();
	        model.addAttribute("productsNew1", productsNew.subList(0,2));
	        model.addAttribute("productsNew2", productsNew.subList(3,5));
	        model.addAttribute("categories", categories);
	        model.addAttribute("products", productPage.getContent());
	        model.addAttribute("currentPage", page);
	        model.addAttribute("totalPages", productPage.getTotalPages());
	        model.addAttribute("url", "/products/"+categoryId);
	        model.addAttribute("sort_option", sortOption);
	        return "shop-grid";
	    }
	    
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
