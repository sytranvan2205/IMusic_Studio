package com.imusicstudio.repository;

import com.imusicstudio.entities.Product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, String> {

	public Page<Product> findProductByProductNameContaining(String name, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
	public Page<Product> findByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

	public List<Product> getProductsByStatusSell(int status);

	public List<Product> findFirst6ByOrderByIdDesc();

}
