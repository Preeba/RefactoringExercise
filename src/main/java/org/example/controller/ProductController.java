package org.example.controller;

import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productServices;

    @Autowired
    public ProductController(ProductService ProductServices) {
        this.productServices = ProductServices;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productServices.getProducts();
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(products);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return productServices.getProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(productServices.getByName(name));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        try {
            Product savedProduct = productServices.addProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving product: " + e.getMessage());
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getByCategory(@PathVariable String category) {
        try {
            return ResponseEntity.ok(productServices.getByCategory(category));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}