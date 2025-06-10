package com.damazon.backend.service;

import com.damazon.backend.model.Product;
import com.damazon.backend.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    public List<Product> addProducts(List<Product> products) {
        return productRepo.saveAll(products);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
}
