package com.iulia.demo.controller;

import com.iulia.demo.model.Product;
import com.iulia.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//This layer is responsible for handling the HTTP requests and responses.
@RestController
@CrossOrigin // This annotation is used to enable Cross-Origin Resource Sharing (CORS) in Spring Boot.
@RequestMapping("/api")
public class ProductController {

    @Autowired // Dependency Injection: Spring will automatically provide the ProductService object
    private ProductService productService;
    // This is a dependency injection of type field injection.
    // it is not recommended to use field injection because it is not clear what dependencies a class has.
    // It is recommended to use constructor injection instead:
//     private final ProductService productService;
//     @Autowired
//     public ProductController(ProductService productService) {
//         this.productService = productService;
//     }

    @RequestMapping("/")
    public String greet(){
        return "Hello from ProductController";
    }

    @GetMapping("/products") // GET method -> GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();

    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = productService.getProduct(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
