package com.imusicstudio.service;

import com.imusicstudio.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    List<Product> getAllProductByCategoryId(int id);

    Optional<Product> getProductById(long id);

    void removeProductById(long id);

    void updateProduct(Product product);

    List<Product> getAllProduct();



}
