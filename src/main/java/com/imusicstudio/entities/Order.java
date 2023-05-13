package com.imusicstudio.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
	@Column(name = "shipaddress", nullable = false)
	private String shipAdress;

	@Column(name = "note")
	private String note;

	@Column(name = "orderdate")
	private Date orderDate;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@Column(name="is_shiped")
	private int isShiped;
	
	@Column(name="status")
	private int status;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderDetail> orderDetails = new ArrayList<>();

	public Order() {
		super();
	}

	public String getShipAdress() {
		return shipAdress;
	}

	public void setShipAdress(String shipAdress) {
		this.shipAdress = shipAdress;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public int getIsShiped() {
		return isShiped;
	}

	public void setIsShiped(int isShiped) {
		this.isShiped = isShiped;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
