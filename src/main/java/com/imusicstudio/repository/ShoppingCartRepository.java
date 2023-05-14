package com.imusicstudio.repository;

import com.imusicstudio.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
//    ShoppingCart save(ShoppingCart cart);
}
