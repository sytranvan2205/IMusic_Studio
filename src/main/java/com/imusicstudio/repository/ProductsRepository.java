package com.imusicstudio.repository;

import com.imusicstudio.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product,String> {

    // Tìm sàn phẩm trong tìm kiếm


}
