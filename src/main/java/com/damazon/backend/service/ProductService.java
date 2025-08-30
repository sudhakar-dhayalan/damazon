package com.damazon.backend.service;

import com.damazon.backend.exception.CustomRunTimeException;
import com.damazon.backend.model.Product;
import com.damazon.backend.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    public List<Product> addProducts(List<Product> products) {
        List<String> existingProductNames = getAllProducts().stream().map(Product::getName).toList();
        products.removeIf(product -> existingProductNames.contains(product.getName()));
//        for (Product existingProduct : getAllProducts()) {
//           products.forEach(product -> {
//               String pName = product.getName();
//               String epName = existingProduct.getName();
//               if (Objects.equals(pName, epName)) {
//                   products.remove(product);
//               }
//           });
//        }

        if (products.isEmpty()) {
            System.out.println("ALL PRODUCTS were already there! ====>>");
//            throw new RuntimeException("No new products to store");
        }
        return productRepo.saveAll(products);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public long deleteAllProducts() {
        int count = productRepo.findAll().size();
        if (count > 0) productRepo.deleteAll();
        return count;
    }

    public Product updateProduct(Product product) {
        Product p = new Product();
        p.setName(product.getName());
        p.setCategory(product.getCategory());
        p.setPrice(product.getPrice());

        Product savedProduct = productRepo.findById(product.getId())
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setCategory(product.getCategory());
                    existingProduct.setPrice(product.getPrice());
                    return productRepo.save(existingProduct);
                })
                .orElse(
                        productRepo.save(p)
                );
        return savedProduct;
    }

    public void deleteProductById(Integer productId) {
        if (productId == null) {
            throw new CustomRunTimeException("Product with Id : " + productId + " not found", "PRODUCT_ID_NULL", HttpStatus.NOT_ACCEPTABLE);
        } else if (productRepo.existsById(productId)) {
            productRepo.deleteById(productId);
        } else {
            throw new CustomRunTimeException("Product with Id : " + productId + " not found", "PRODUCT_NOT_FOUND", HttpStatus.NOT_FOUND);
        }
    }

    public Page<Product> getProductsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return  productRepo.findAll(pageable);
    }
}
