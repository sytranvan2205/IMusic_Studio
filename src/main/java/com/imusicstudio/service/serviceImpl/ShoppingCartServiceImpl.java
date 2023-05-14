package com.imusicstudio.service.serviceImpl;

import com.imusicstudio.entities.CartItem;
import com.imusicstudio.entities.Product;
import com.imusicstudio.entities.ShoppingCart;
import com.imusicstudio.entities.User;
import com.imusicstudio.repository.CartItemRepository;
import com.imusicstudio.repository.ShoppingCartRepository;
import com.imusicstudio.service.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartServices {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Override
    public ShoppingCart addItemToCart(Product product, int quantity, User user) {
        ShoppingCart cart=user.getShoppingCart();
        if (cart==null){
            cart=new ShoppingCart();
        }

        Set<CartItem> cartItems=cart.getCartItems();
        CartItem cartItem=findCartItem(cartItems,product.getId());
        if (cartItems==null){
            cartItems=new HashSet<>();
            if (cartItem==null){
                cartItem=new CartItem();
                cartItem.setProduct(product);
                cartItem.setTotalPrice(quantity * product.getProductPrice());
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                cartItems.add(cartItem);
                cartItemRepository.save(cartItem);
            }
        }else {

                if (cartItem==null) {
                    cartItem = new CartItem();
                    cartItem.setProduct(product);
                    cartItem.setTotalPrice(quantity * product.getProductPrice());
                    cartItem.setQuantity(quantity);
                    cartItem.setCart(cart);
                    cartItems.add(cartItem);
                    cartItemRepository.save(cartItem);
                }else {
                    cartItem.setQuantity(cartItem.getQuantity()+quantity);
                    cartItem.setTotalPrice(cartItem.getTotalPrice()+(quantity* product.getProductPrice()));
                    cartItemRepository.save(cartItem);
                }
        }
        cart.setCartItems(cartItems);

        int totalItems=totalItems(cart.getCartItems());
        double totalPrice=totalPrice(cart.getCartItems());

        cart.setTotalPrices((totalPrice));
        cart.setTotalItems(totalItems);
        cart.setUser(user);
        return shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCart updateItemIncart(Product product, int quantity, User user) {
        ShoppingCart cart=user.getShoppingCart();

        Set<CartItem> cartItems=cart.getCartItems();

        CartItem cartItem=findCartItem(cartItems,product.getId());

        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(quantity * product.getProductPrice());
        cartItemRepository.save(cartItem);

        int totalItems=totalItems(cartItems);
        double totalPrice=totalPrice(cartItems);
        cart.setTotalItems(totalItems);
        cart.setTotalPrices(totalPrice);

        return shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCart deleteItemFromCart(Product product, User user) {
        ShoppingCart cart=user.getShoppingCart();

        Set<CartItem> cartItems=cart.getCartItems();

        CartItem item=findCartItem(cartItems,product.getId());

        cartItems.remove(item);
        cartItemRepository.delete(item);

        double totalPrice=totalPrice(cartItems);
        int totalItems=totalItems(cartItems);

        cart.setTotalPrices(totalPrice);
        cart.setTotalItems(totalItems);
        cart.setCartItems(cartItems);
        return shoppingCartRepository.save(cart);
    }

    //Tìm vật phẩm trong giỏ hàng
private CartItem findCartItem(Set<CartItem> cartItems,Long productId){
        //nếu giỏ hàng rỗng thì trả về null
        if (cartItems==null){
            return null;
        }
        //ngược lại thì tìm các vật phẩm có trong giỏ hàng
        CartItem cartItem=null;
        for (CartItem item:cartItems){
            if (item.getProduct().getId()==productId){
                cartItem=item;
            }
        }
        return cartItem;
}
    private int totalItems(Set<CartItem>cartItems){
        int totalItems=0;
        for (CartItem item:cartItems){
            totalItems +=item.getQuantity();
        }
        return totalItems;
    }
    private double totalPrice(Set<CartItem>cartItems){
        double totalPrice=0.0;
        for (CartItem item: cartItems){
            totalPrice+=item.getTotalPrice();
        }
        return totalPrice;
    }
}
