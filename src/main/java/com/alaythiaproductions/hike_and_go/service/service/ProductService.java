package com.alaythiaproductions.hike_and_go.service.service;

import com.alaythiaproductions.hike_and_go.model.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    List<Product> findAll();
}
