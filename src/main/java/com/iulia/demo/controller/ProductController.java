package com.iulia.demo.controller;

import com.iulia.demo.model.Product;
import com.iulia.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

//    @GetMapping("/products") // GET method -> GetMapping
//    public List<Product> getAllProducts(){
//        return productService.getAllProducts();
//
//    }
    @GetMapping("/products")
    // instead of public List<Product> getAllProducts() {....
    // we use ResponseEntity-> a class that represents an HTTP response, including headers, body, and status.
    public ResponseEntity<List<Product>> getAllProducts() {

        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = productService.getProduct(id);
        // in product service este .orElse(null); => daca s-a returnat null, atunci returnez codul 404
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile) {
        // RequestBody -> accept the whole object as a JSON
        //RequestPart -> accept the object as a part of the form data
        try{
            System.out.println("Product: " + product);
            Product prod = productService.addProduct(product, imageFile);
            return new ResponseEntity<>(prod, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product = productService.getProduct(productId);
        byte[] imageFile = product.getImageData();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
        // we send the content type of the image and the image file as a byte array
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable int productId,
                                                @RequestPart Product prod,
                                                @RequestPart MultipartFile imageFile){

        Product product = null;
        try {
            product = productService.updateProduct(productId, prod, imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
        if(product != null){
           return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
       }
       else{
           return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
       }
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId){
        Product product = productService.getProduct(productId);
        if(product != null){
            productService.deleteProduct(productId);
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Failed to delete", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        System.out.println("Searching for: " + keyword);
        List<Product> products = productService.searchProducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
