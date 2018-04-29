package com.alaythiaproductions.hike_and_go.repository;

import com.alaythiaproductions.hike_and_go.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(String category);

    List<Product> findByNameContaining(String name);

}
