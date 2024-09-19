package com.iulia.demo.service;

import com.iulia.demo.model.Product;
import com.iulia.demo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
//This layer is responsible for implementing the business logic of the application.
// It receives data from the repository layer and sends it to the controller layer.

@Service
public class ProductService {
// I don't want the Service to communicate directly with the database,
// so I will create a repository class that will handle the communication with the database.

    @Autowired // Dependency Injection: Spring will automatically provide the ProductRepo object
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        List<Product> products = repo.findAll();
        System.out.println("Products: " + products);
        return products;
    }

    public Product getProduct(int id) {
        return repo.findById(id).orElse(null); // findById returns an Optional object

    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }

    public Product updateProduct(int productId, Product prod, MultipartFile imageFile) throws IOException {
        prod.setImageData(imageFile.getBytes());
        prod.setImageName(imageFile.getOriginalFilename());
        prod.setImageType(imageFile.getContentType());
        return repo.save(prod);
    }

    public void deleteProduct(int productId) {
        repo.deleteById(productId);
    }

    public List<Product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}
