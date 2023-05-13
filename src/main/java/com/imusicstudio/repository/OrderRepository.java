package com.imusicstudio.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.imusicstudio.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(value = "select * from orders where status = 1 and is_shiped = 1 limit ?,?", nativeQuery = true)
	List<Order> findOrderShiped(int offset, int limit);

	@Transactional
	@Modifying
	@Query(value = "UPDATE orders SET is_shiped = 0 WHERE id =?", nativeQuery = true)
	int updateIsShiped(Long id);

	Page<Order> findAllByIsShipedAndStatus(Pageable pageable, int isShiped, int isStatus);

	@Query(value = "select SUM(odt.detailquantity*p.product_price) as total_price "
			+ "from orderdetail as odt "
			+ "join product as p on odt.product_id = p.id "
			+ "join orders as od on odt.order_id=od.id "
			+ "where od.id = ?;", nativeQuery = true)
	Long getTotalPriceByOrderId(Long id);
	
//	@Query(value = "select  od.*,"
//			+ "SUM(odt.detailquantity*p.product_price) as total_price from orderdetail as odt "
//			+ "join product as p on odt.product_id = p.id "
//			+ "join orders as od on odt.order_id=od.id "
//			+ "where od.is_shiped =1 and od.status =1 "
//			+ "group by (od.id) limit ?,?", nativeQuery = true)
//	Page<Object> getOrdersByIsShipedAndStatus(Long offset, int limit);
}
