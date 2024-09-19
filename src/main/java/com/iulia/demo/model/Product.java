package com.iulia.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

//@Component // This tells Spring to manage this class
@Entity // This tells Hibernate to make a table out of this class
@Table(name = "product")
public class Product {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment the id
    private int productId;

    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private boolean available;
    private int quantity;

    private String imageName;
    private String imageType;

    @Lob // Large Object - for storing large data
    private byte[] imageData;
    public Product() {
    }

    public Product(int productId, String name, String description, String brand, BigDecimal price, String category, LocalDate releaseDate, boolean available, int quantity, String imageName, String imageType, byte[] imageData) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.releaseDate = releaseDate;
        this.available = available;
        this.quantity = quantity;
        this.imageName = imageName;
        this.imageType = imageType;
        this.imageData = imageData;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", releaseDate=" + releaseDate +
                ", available=" + available +
                ", quantity=" + quantity +
                ", imageName='" + imageName + '\'' +
                ", imageType='" + imageType + '\'' +
                ", imageData=" + Arrays.toString(imageData) +
                '}';
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
