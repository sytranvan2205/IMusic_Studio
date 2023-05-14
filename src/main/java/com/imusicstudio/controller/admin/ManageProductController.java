package com.imusicstudio.controller.admin;

import com.imusicstudio.entities.Category;
import com.imusicstudio.entities.Product;
import com.imusicstudio.service.serviceImpl.CategoryServiceImpl;
import com.imusicstudio.service.serviceImpl.CloudinaryServiceImpl;
import com.imusicstudio.service.serviceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ManageProductController {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CategoryServiceImpl categoryService;

    private CloudinaryServiceImpl cloudinaryService = new CloudinaryServiceImpl();;
    @GetMapping("/admin/manage-product")
    public String loadProduct(Model model){
        List<Product> productList = productService.getAllProduct();
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("products", productList);
        return "admin/crud-product";
    };
    @GetMapping("/add-product")
    public String addFormProduct( Model model){
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        return "admin/add-product";
    };
    @GetMapping("/update-product/{productid}")
    public String updateFormProduct(@PathVariable(value = "productid") Long id, Model model){
        Product product = productService.findById(id);
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        return "admin/update-product";
    };
    @PostMapping("/save-product")
    public String addProduct(@RequestParam("name") String name,
                             @RequestParam("price") Long price,
                             @RequestParam("description") String description,
                             @RequestParam("quantity") int quantity,
                             @RequestParam("category") Long categoryId,
                             @RequestParam("image") MultipartFile image) {
        try {
            // Lưu hình ảnh lên Cloudinary và lấy đường dẫn của hình ảnh
            String imageUrl = cloudinaryService.uploadImage(image);

            // Tạo đối tượng Product với thông tin từ form và đường dẫn hình ảnh
            Category category = categoryService.getCategoryById(categoryId);
            Product product = new Product();
            product.setProductName(name);
            product.setProductDesc(description);
            product.setProductImage(imageUrl);
            product.setProductPrice(price);
            product.setQuantity(quantity);
            product.setCategory(category);
            if (quantity == 0){
                product.setStatusSell(0);
            } else if (quantity > 0) {
                product.setStatusSell(1);
            }

            // Lưu sản phẩm vào database
            productService.save(product);


        } catch (Exception e) {
            // Xử lý lỗi nếu có
        }
        return "admin/fragments-Admin";
    }
    @PostMapping("/update-product")
    public String updateProduct(@RequestParam("id") Long id,
                            @RequestParam("name") String name,
                             @RequestParam("price") Long price,
                             @RequestParam("description") String description,
                             @RequestParam("quantity") int quantity,
                             @RequestParam("category") Long categoryId,
                             @RequestParam("image") MultipartFile image) {

        Product product = productService.findById(id);
        cloudinaryService.deleteImage(product.getProductImage());
        try {
            // Lưu hình ảnh lên Cloudinary và lấy đường dẫn của hình ảnh
            String imageUrl = cloudinaryService.uploadImage(image);

            // Tạo đối tượng Product với thông tin từ form và đường dẫn hình ảnh
            Category category = categoryService.getCategoryById(categoryId);
            product.setProductName(name);
            product.setProductDesc(description);
            product.setProductImage(imageUrl);
            product.setProductPrice(price);
            product.setQuantity(quantity);
            product.setCategory(category);
            if (quantity == 0){
                product.setStatusSell(0);
            } else if (quantity > 0) {
                product.setStatusSell(1);
            }
            // Lưu sản phẩm vào database
            productService.update(product);

            return "redirect:/admin/manage-product";
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            e.printStackTrace();
            return "redirect:/admin/manage-product";
        }
    }



}
