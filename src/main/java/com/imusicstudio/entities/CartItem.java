package com.imusicstudio.entities;

import javax.persistence.*;

@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    public CartItem(ShoppingCart cart) {
        this.cart = cart;
    }

    private int quantity;
    private double totalPrice;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id",referencedColumnName = "id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart cart;

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CartItem() {
    }

    public CartItem(Long id, int quantity, double totalPrice, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.product = product;
    }
}
