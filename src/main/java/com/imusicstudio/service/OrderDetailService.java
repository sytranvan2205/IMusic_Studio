package com.imusicstudio.service;

import java.util.List;

import com.imusicstudio.entities.OrderDetail;

public interface OrderDetailService {
//	List<OrderDetail> getAllOrderDetailById(Long id);

	List<OrderDetail> getAllOrderDetailByOrderId(Long id);
}
