package com.felipe.springcloud.msvc.products.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.felipe.springcloud.msvc.products.entities.Product;
import com.felipe.springcloud.msvc.products.services.ProductService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Product> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getMethodName(@PathVariable Long id) {
        Optional<Product> producOptional = service.findById(id);
        if (producOptional.isPresent()) {
            return ResponseEntity.ok(producOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
