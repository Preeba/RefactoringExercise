package org.example.service;

import org.example.model.Product;
import org.example.reporitory.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(UUID id) {
        return this.productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    //localhost:8080/api/products/search?name=Lego
    // finds all products whose name contains the input string (case-insensitive).
    public List<Product> getByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}
