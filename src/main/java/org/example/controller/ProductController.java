package org.example.controller;

import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productServices) {
        this.productService = productServices;
    }

    //curl --location 'localhost:8080/api/products'
    // localhost:8080/api/products/search?name=Lego%20Mario
    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestParam(required=false, name = "name") String name) {
      try {
          if (name != null) {
              return ResponseEntity.ok(productService.getByName(name));
          } else {
              return ResponseEntity.ok(productService.getProducts());
          }
      } catch(Exception e) {
          System.out.println(e.getMessage());
          return ResponseEntity.notFound().build();
      }
    }

    //localhost:8080/api/products/ec541e16-0019-41fd-9b3d-6c2229086c0a
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable UUID id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //localhost:8080/api/products/search?name=Lego%20Mario
    @GetMapping("/search") // custom sub-path for search by name
    public ResponseEntity<?> getByName(@RequestParam(name="name") String name) {
        try {
            return ResponseEntity.ok(productService.getByName(name));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        try {
            Product savedProduct = productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving product: " + e.getMessage());
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getByCategory(@PathVariable String category) {
        try {
            return ResponseEntity.ok(productService.getByCategory(category));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}