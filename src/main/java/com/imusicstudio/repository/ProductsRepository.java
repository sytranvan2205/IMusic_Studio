package com.imusicstudio.repository;

import com.imusicstudio.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product,String> {
    List<Product> findAllByCategory_Id(long id);
}
