package com.alaythiaproductions.hike_and_go.service.implementation;

import com.alaythiaproductions.hike_and_go.model.Product;
import com.alaythiaproductions.hike_and_go.repository.ProductRepository;
import com.alaythiaproductions.hike_and_go.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
