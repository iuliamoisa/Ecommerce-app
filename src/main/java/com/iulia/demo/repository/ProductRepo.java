package com.iulia.demo.repository;

import com.iulia.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// This layer is responsible for interacting with the database.

// This annotation is used to indicate that the class provides the mechanism for storage, retrieval,
// search, update and delete operation on objects.
@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    //JpaRepository<Class_Im_Working_With, Type_Of_The_Primary_Key>


    //JPQL (Java Persistence Query Language) is a query language defined in JPA specification.
    // It is used to create queries against entities to store in a relational database.
    // The queries are defined as strings, similarly to SQL queries.
    // The difference is that JPQL queries operate on entities rather than tables.
    @Query("SELECT p from Product p WHERE "+
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchProducts(String keyword); // search for products by keyword
}
