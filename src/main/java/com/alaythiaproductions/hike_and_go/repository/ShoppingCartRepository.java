package com.alaythiaproductions.hike_and_go.repository;

import com.alaythiaproductions.hike_and_go.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
