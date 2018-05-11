package com.alaythiaproductions.hike_and_go.repository;

import com.alaythiaproductions.hike_and_go.model.CartItem;
import com.alaythiaproductions.hike_and_go.model.TravelToCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TravelToCartItemRepository extends JpaRepository<TravelToCartItem, Long> {

    void deleteByCartItem(CartItem cartItem);

}
