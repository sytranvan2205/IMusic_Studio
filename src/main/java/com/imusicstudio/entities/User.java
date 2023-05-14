package com.imusicstudio.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User extends BaseEntity {
	@Column(name = "username", nullable = false, unique = true)
	private String userName;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "fullname", nullable = false, length = 80)
	private String fullName;
	@Column(name = "useremail", nullable = false, unique = true, length = 80)
	private String userEmail;
	@Column(name = "phone_number")
	private String phoneNumber;
	private String address;

	@OneToOne(mappedBy = "user")
	private ShoppingCart shoppingCart;



	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Order> orders = new ArrayList<>();

	@Column(name = "status", nullable = false)
	private int status;
//	@OneToMany(mappedBy = "user")
//	private Set<SecureToken> tokens;
//	private boolean accountVerified;

	public User() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Collection<Role> getRoles() {
		return roles;
	}
}
