package com.imusicstudio.service.serviceImpl;

import com.imusicstudio.entities.Product;
import com.imusicstudio.repository.ProductsRepository;
import com.imusicstudio.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    ProductsRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }//findAll

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }//add or update (tuy vao pri-key)

    @Override
    public void removeProductById(long id) {
        productRepository.deleteById(String.valueOf(id));
    }//delete dua vao pri-key

    @Override
    public Optional<Product> getProductById(long id) {
        return productRepository.findById(String.valueOf(id));
    }//search theo id

    @Override
    public List<Product> getAllProductByCategoryId(long id) {
        return productRepository.findAllByCategory_Id(id);
    }
    //findList theo ProductDTO.categoryId
}
