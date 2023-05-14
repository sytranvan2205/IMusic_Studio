package com.imusicstudio.service;

import com.imusicstudio.entities.Product;
import com.imusicstudio.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(long id) {
        return productRepository.findById(id);
    }
}

