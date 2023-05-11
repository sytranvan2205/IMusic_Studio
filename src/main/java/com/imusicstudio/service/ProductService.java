package com.imusicstudio.service;

import com.imusicstudio.entities.Product;

import java.util.List;

public interface Products {
    List<Product> getNameProductFromSearch(String nameproduct);
    List<Product> getAllProducts(Product product);



}
