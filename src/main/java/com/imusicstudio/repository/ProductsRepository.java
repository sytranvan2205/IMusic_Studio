package com.imusicstudio.repository;

import com.imusicstudio.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product,Long> {

    // Tìm sàn phẩm trong tìm kiếm
//    Product getProductById(long id);

}
