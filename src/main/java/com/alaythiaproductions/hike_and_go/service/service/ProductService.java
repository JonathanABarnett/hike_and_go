package com.alaythiaproductions.hike_and_go.service.service;

import com.alaythiaproductions.hike_and_go.model.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    List<Product> findAll();

    Product findOne(Long id);

    void removeOne(Long id);

    List<Product> findByCategory(String category);

    List<Product> blurrySearch(String keyword);
}
