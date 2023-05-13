package com.imusicstudio.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.imusicstudio.entities.Order;

public interface OrderService {
	List<Order> getOrderShiped(int limit,int offset);
	int getTotalItem();
	Page<Order> findAll(Pageable pageable);
	Page<Order> getOrderByIsShipedAndStatus(Pageable pageable);
	boolean updateIsShiped(Long id);
	Long getTotalPriceByOrderId(Long id);
}
