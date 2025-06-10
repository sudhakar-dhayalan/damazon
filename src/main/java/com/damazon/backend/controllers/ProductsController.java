package com.damazon.backend.controllers;

import com.damazon.backend.model.Product;
import com.damazon.backend.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@Transactional
public class ProductsController {
    @Autowired
    ProductService productService;

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("add")
    public ResponseEntity<List<Product>> addProduct(@RequestBody List<Product> products) {
        return ResponseEntity.ok(productService.addProducts(products));
    }
}
