package com.alaythiaproductions.hike_and_go.service.service;

import com.alaythiaproductions.hike_and_go.model.*;
import com.alaythiaproductions.hike_and_go.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CartItemService {

    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

    CartItem updateCartItem(CartItem cartItem);

    CartItem addProductToCartItem(Product product, User user, Integer quantity);

//    CartItem addTravelToCartItem(Travel travel, User user, Integer quantity);

    CartItem findById(Long cartItemId);

    void removeCartItem(CartItem cartItem);

    CartItem save(CartItem cartItem);

    List<CartItem> findByOrder(Order order);


}
