package com.foodapi.food_service.service;

import com.foodapi.food_service.exception.ProductAPIRequestException;
import com.foodapi.food_service.model.ProductModel;

import com.foodapi.food_service.model.ProductModel;

import com.foodapi.food_service.repo.ProductRepo;
//inject repository into service


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

//write the business logic
//definition of our functions

@Service
public class ProductService implements ProductServiceRepo{

    private final ProductRepo productRepository;

    @Autowired  //This allows the service to interact with the database through the repository.
    public ProductService(ProductRepo productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductModel> getAllProducts() {
        List<ProductModel> products = new ArrayList<ProductModel>();
        productRepository.findAll()
                .forEach(product -> products.add(product));
        return products;
    }

    public ProductModel getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductModel createProduct(ProductModel product) {
        // Additional logic/validation can be implemented here

        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return productRepository.save(product) ;
    }

    public ProductModel updateProduct(Long id, ProductModel product) {
        ProductModel existingProduct = getProductById(id);
        // Update the attributes of the existingProduct with the attributes from the product
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setImage(product.getImage());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        ProductModel product = getProductById(id);
        productRepository.delete(product);
    }

    //search and filter
    public List<ProductModel> findByCategory(String productName,  String categoryName) {
        try {
            return productRepository.findByCategory(productName, categoryName);
        } catch (Exception ex) {
            // Handle the specific exception thrown by the repository
            throw new ProductAPIRequestException("Error while retrieving products by category: " + ex.getMessage());
        }
    }

}

