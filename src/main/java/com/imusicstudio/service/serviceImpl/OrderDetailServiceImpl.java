package com.imusicstudio.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imusicstudio.entities.OrderDetail;
import com.imusicstudio.repository.OrderDetailRepository;
import com.imusicstudio.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailRepository repo;
	

	@Override
	public List<OrderDetail> getAllOrderDetailByOrderId(Long id) {
		return repo.findListByOrderId(id);
	}
	
}
