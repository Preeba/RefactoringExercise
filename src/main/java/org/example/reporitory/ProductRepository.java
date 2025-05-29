package org.example.reporitory;


import org.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findByCategory(@Param("category") String category);

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    List<Product> findByName(@Param("name") String name);

    // all products whose name contains the input string (case-insensitive).
    List<Product> findByNameContainingIgnoreCase(@Param("name") String name);

    // Exact match ignore case
    List<Product> findByNameIgnoreCase(@Param("name") String name);

}