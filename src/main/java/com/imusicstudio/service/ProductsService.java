package com.imusicstudio.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.imusicstudio.entities.Product;

public interface ProductsService {
    Page<Product> getNameProductFromSearch(String nameproduct, int page, Sort sort);

    void save(Product product);


    Page<Product> findAll(int page, Sort sort);

    List<Product> getProductsByStatus(int status);
    List<Product> get6NewsestProduct();

    List<Product> getAllProductByCategoryId(long id);

//    Optional<Product> getProductById(Long id);
//
//    void removeProductById(long id);

    void updateProduct(Product product);

    List<Product> getAllProduct();
//    Product getProductById(long id);

    Page<Product>  getProductsByCategory(long category, int page, Sort sort);

//	Optional<Product> getProductById(Long id);

	Product getProductById(Long id);

	void removeProductById(Long id);
}
