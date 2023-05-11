package com.imusicstudio.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart_item")
public class CartItem extends BaseEntity{
	@ManyToOne
	@JoinColumn(name="cart_id",nullable = false)
	private Cart cart;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="product_id",referencedColumnName = "id")
	private Product product;
	
	private int quantity;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
