package com.alaythiaproductions.hike_and_go.repository;

import com.alaythiaproductions.hike_and_go.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {



}
