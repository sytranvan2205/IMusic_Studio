package com.imusicstudio.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderdetail")
public class OrderDetail extends BaseEntity {

	@Column(name = "detailquantity", nullable = false)
	private int detailQuantity;

	@ManyToOne(fetch = FetchType.EAGER)
	private Order order;

	@ManyToOne(fetch = FetchType.EAGER)
	private Product product;

	public OrderDetail() {
		super();
	}

	public int getDetailQuantity() {
		return detailQuantity;
	}

	public void setDetailQuantity(int detailQuantity) throws Exception {
		if (detailQuantity <= product.getQuantity()) {
			this.detailQuantity = detailQuantity;
		} else {
			throw new Exception("Amount of product not applied");
		}

	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
