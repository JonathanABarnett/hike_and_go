package com.alaythiaproductions.hike_and_go.repository;

import com.alaythiaproductions.hike_and_go.model.ProductToCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductToCartItemRepository extends JpaRepository<ProductToCartItem, Long> {
}
