package com.imusicstudio.service;

import com.imusicstudio.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductsService {
    Page<Product> getNameProductFromSearch(String nameproduct, int page);
    void save(Product product);


    Page<Product> findAll(int page);

    List<Product> getProductsByStatus(int status);
    List<Product> get6NewsestProduct();


    Page<Product>  getProductsByCategory(long category, int page);
}
