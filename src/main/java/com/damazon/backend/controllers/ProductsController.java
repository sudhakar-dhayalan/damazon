package com.damazon.backend.controllers;

import com.damazon.backend.model.Products;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
@Transactional
public class ProductsController {

    @GetMapping("")
    public String test() {
        return "test";
    }

    @PostMapping("add")
    public ResponseEntity<?> addProduct(@Valid @RequestBody Products products) {
        return new ResponseEntity<>("working",HttpStatus.ACCEPTED);
    }
}
