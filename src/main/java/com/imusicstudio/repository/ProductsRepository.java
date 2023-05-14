package com.imusicstudio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imusicstudio.entities.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product,String> {
    List<Product> findAllByCategory_Id(long id);
}
