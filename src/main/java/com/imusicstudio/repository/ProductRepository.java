package com.imusicstudio.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.imusicstudio.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public Page<Product> findProductByProductNameContaining(String name, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
	public Page<Product> findByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

	public List<Product> getProductsByStatusSell(int status);

	public List<Product> findFirst6ByOrderByIdDesc();

	Product findById(long Id);

	List<Product> findAllByCategory_Id(long id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE `imusic_studio`.`product` SET product_name = :name, product_price = :price, product_desc = :description, product_img = :image, product_stock = :quantity,status = :statusSell, category_id = :category   WHERE id = :id", nativeQuery = true)
	void updateproduct(@Param("id") Long id, @Param("name") String name, @Param("price") Long price,
			@Param("description") String description, @Param("category") Long category,
			@Param("statusSell") Integer status, @Param("quantity") Integer quantity, @Param("image") String image);
}
