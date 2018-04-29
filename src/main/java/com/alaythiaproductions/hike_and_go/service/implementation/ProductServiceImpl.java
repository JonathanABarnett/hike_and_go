package com.alaythiaproductions.hike_and_go.service.implementation;

import com.alaythiaproductions.hike_and_go.model.Product;
import com.alaythiaproductions.hike_and_go.repository.ProductRepository;
import com.alaythiaproductions.hike_and_go.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
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
        List<Product> productList =  productRepository.findAll();
        List<Product> activeProductList = new ArrayList<>();

        for (Product product : productList) {
            if (product.isActive()) {
                activeProductList.add(product);
            }
        }
        return activeProductList;
    }

    @Override
    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public void removeOne(Long id) {
        productRepository.delete(id);
    }

    @Override
    public List<Product> findByCategory(String category) {
        List<Product> productList = productRepository.findByCategory(category);

        List<Product> activeProductList = new ArrayList<>();

        for (Product product : productList) {
            if (product.isActive()) {
                activeProductList.add(product);
            }
        }
        return activeProductList;
    }

    @Override
    public List<Product> blurrySearch(String name) {
        List<Product> productList = productRepository.findByNameContaining(name);

        List<Product> activeProductList = new ArrayList<>();

        for (Product product : productList) {
            if (product.isActive()) {
                activeProductList.add(product);
            }
        }
        return activeProductList;

    }
}
