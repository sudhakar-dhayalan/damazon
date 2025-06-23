package com.damazon.backend.controllers;

import com.damazon.backend.model.Product;
import com.damazon.backend.service.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<Product>> addProduct(@RequestBody List<@Valid Product> products) {
        return ResponseEntity.ok(productService.addProducts(products));
    }

    @PutMapping("update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @DeleteMapping("all")
    public ResponseEntity<?> deleteAllProducts() {
        long countOfAssetsDeleted = productService.deleteAllProducts();
        // Map<String, Object> response = Map.of("deleted", countOfAssetsDeleted);
        return ResponseEntity.ok(Map.of("deleted", countOfAssetsDeleted));
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<?> deleteProductById(@PathVariable Integer productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.ok("Deleted");
    }
}
