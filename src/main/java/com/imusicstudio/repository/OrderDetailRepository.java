package com.imusicstudio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imusicstudio.entities.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
	
	@Query(value = "select * from orderdetail where order_id = ?", nativeQuery = true)
	List<OrderDetail> findListByOrderId(Long id);
}
