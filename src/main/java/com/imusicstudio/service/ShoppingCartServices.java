package com.imusicstudio.service;

import com.imusicstudio.entities.CartItem;
import com.imusicstudio.entities.Product;
import com.imusicstudio.entities.ShoppingCart;
import com.imusicstudio.entities.User;
import org.springframework.stereotype.Service;


public interface ShoppingCartServices {
    ShoppingCart addItemToCart(Product product, int quantity, User user);

    ShoppingCart updateItemIncart(Product product,int quantity,User user);

    ShoppingCart deleteItemFromCart(Product product,User user);

    ShoppingCart saveCartItem(CartItem cartItem);

    ShoppingCart saveShoppingCart(ShoppingCart shoppingCart);
}
