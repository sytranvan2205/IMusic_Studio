package com.imusicstudio.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart extends BaseEntity{
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private User user;
	
	@OneToMany(mappedBy = "cart")
	private Set<CartItem> cartItems;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	
	
	
	
	
}
