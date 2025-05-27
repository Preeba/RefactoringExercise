package org.example.service;


import org.example.model.Product;
import org.example.reporitory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return this.productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        Product newProduct = new Product();
        UUID id = UUID.randomUUID();
        System.out.println("ID ... "+ id);
        newProduct.setId(id);
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setCategory(product.getCategory());
        this.productRepository.save(product);
        return newProduct;
    }

    public List<Product> getByCategory(String category){
        return this.productRepository.findByCategory(category);
    }

    public List<Product> getByName(String name){
        return this.productRepository.findByName(name);
    }
}