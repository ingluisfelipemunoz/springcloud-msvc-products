package com.felipe.springcloud.msvc.products.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.springcloud.msvc.products.entities.Product;
import com.felipe.springcloud.msvc.products.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    final private ProductRepository repository;
    private final Environment environment;

    public ProductServiceImpl(ProductRepository productRepository, Environment environment) {
        this.repository = productRepository;
        this.environment = environment;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        final int port = Integer.parseInt(environment.getProperty("local.server.port"));
        return ((List<Product>) repository.findAll()).stream().map(product -> {
            product.setPort(port);
            return product;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        final int port = Integer.parseInt(environment.getProperty("local.server.port"));
        return repository.findById(id).map(product -> {
            product.setPort(port);
            return product;
        });
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return this.repository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

}
