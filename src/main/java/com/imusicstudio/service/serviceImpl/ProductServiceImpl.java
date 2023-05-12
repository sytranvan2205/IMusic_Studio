package com.imusicstudio.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.imusicstudio.entities.Product;
import com.imusicstudio.repository.ProductsRepository;
import com.imusicstudio.service.ProductsService;

@Service
public class ProductServiceImpl implements ProductsService{

	private final ProductsRepository productRepository;
	@Autowired
	public ProductServiceImpl(ProductsRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Page<Product> getNameProductFromSearch(String nameproduct, int page , Sort sort) {
		int pageSize = 9; // Number of products per page
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		return productRepository.findProductByProductNameContaining(nameproduct, pageable);
	}


	@Override
	public void save(Product product) {
		productRepository.save(product);
		
	}

	@Override
	public Page<Product> findAll(int page, Sort sort) {
		int pageSize = 9; // Number of products per page
		Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
		return productRepository.findAll(pageable);
	}

	@Override
	public List<Product> getProductsByStatus(int status) {
		return  productRepository.getProductsByStatusSell(status);
	}

	@Override
	public List<Product> get6NewsestProduct() {
		return productRepository.findFirst6ByOrderByIdDesc();
	}

	@Override
	public Page<Product>  getProductsByCategory(long category, int page , Sort sort) {
		int pageSize = 9; // Number of products per page
		Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
		return productRepository.findByCategoryId(category, pageable);
	}

}
