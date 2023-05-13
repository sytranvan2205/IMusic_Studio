package com.imusicstudio.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.imusicstudio.entities.Order;
import com.imusicstudio.repository.OrderRepository;
import com.imusicstudio.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepo;

	@Override
	public List<Order> getOrderShiped(int limit,int offset) {
		List<Order> orders = orderRepo.findOrderShiped(offset, limit);
		return orders;
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return (int)orderRepo.count();
	}
	
	@Override
	public Page<Order> findAll(Pageable pageable) {
		return orderRepo.findAll(pageable);
	}
	
	@Override
	public boolean updateIsShiped(Long isShiped) {
		int count = orderRepo.updateIsShiped(isShiped);
		if(count>0)
			return true;
		return false;
	}

	@Override
	public Page<Order> getOrderByIsShipedAndStatus(Pageable pageable) {
//		return orderRepo.getOrdersByIsShipedAndStatus(pageable.getOffset(),pageable.getPageSize());
		return orderRepo.findAllByIsShipedAndStatus(pageable, 1, 1);
	}
	
	@Override
	public Long getTotalPriceByOrderId(Long id) {
		return orderRepo.getTotalPriceByOrderId(id);
	}
}
