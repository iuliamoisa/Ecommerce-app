package com.iulia.demo.repository;

import com.iulia.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This layer is responsible for interacting with the database.
@Repository
// This annotation is used to indicate that the class provides the mechanism for storage, retrieval,
// search, update and delete operation on objects.

public interface ProductRepo extends JpaRepository<Product, Integer> {
    //JpaRepository<Class_Im_Working_With, Type_Of_The_Primary_Key>
}
