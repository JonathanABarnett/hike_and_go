package com.alaythiaproductions.hike_and_go.repository;

import com.alaythiaproductions.hike_and_go.model.CartItem;
import com.alaythiaproductions.hike_and_go.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
}
