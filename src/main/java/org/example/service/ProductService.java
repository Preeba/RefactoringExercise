package org.example.service;


import org.example.model.Product;
import org.example.reporitory.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface ProductService {
    public List<Product> getProducts();
    public List<Product> getByName(String name);
    public Optional<Product> getProductById(UUID id);
    public Product addProduct(Product product);
    public List<Product> getByCategory(String category);
}