package com.imusicstudio.service;

import com.imusicstudio.entities.Product;
import com.imusicstudio.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductServices {


     Product getProductById(long id);

}
