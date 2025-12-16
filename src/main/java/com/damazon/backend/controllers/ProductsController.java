package com.damazon.backend.controllers;

import com.damazon.backend.model.Product;
import com.damazon.backend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("products")
public class ProductsController {
    @Autowired
    ProductService productService;

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products")
    public Page<Product> getHistory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return productService.getProductsWithPagination(page, size);
    }

    @PostMapping()
    public ResponseEntity<List<Product>> addProduct(@RequestBody List<@Valid Product> products) {
        return ResponseEntity.ok(productService.addProducts(products));
    }

    @PutMapping()
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @DeleteMapping()
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
