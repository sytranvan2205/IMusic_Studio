package com.imusicstudio.service.impl;

import com.imusicstudio.entities.Product;
import com.imusicstudio.repository.ProductsRepository;
import com.imusicstudio.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductServices {

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public Product getProductById(long id) {
        return productsRepository.findById(id).orElse(null);
    }
}
