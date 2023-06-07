package com.foodapi.food_service.model;

import jakarta.persistence.*;

import javax.validation.constraints.*;

@Entity
@Table(name = "products")

public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "name")
    @NotBlank(message = "Product name is required!")
    private String name;

    @Column(name = "price")
    @NotBlank(message = "Product Price is required")
    private double price;

    @Column(name = "description")
    @NotNull(message = "Description cannot be empty!")
    private String description;

    //updates the table to get the latest data
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private CategoryModel category;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private ImageModel image;

     //Constructors - to initialize the variables or to assign an initial value

    public ProductModel() {
    }

    public ProductModel(Long productId, String name, double price, String description, CategoryModel category, ImageModel image) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
    }

    public ProductModel(String errorMessage) {
    }

    // getters - to get the value, (returns the variable value)
    // setters - to set the value, (sets the value.)


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public ImageModel getImage() {
        return image;
    }

    public void setImage(ImageModel image) {
        this.image = image;
    }
}
