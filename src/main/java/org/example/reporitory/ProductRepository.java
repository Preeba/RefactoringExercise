package org.example.reporitory;


import org.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findByCategory(@Param("category") String category);

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    List<Product> findByName(@Param("name") String name);

}