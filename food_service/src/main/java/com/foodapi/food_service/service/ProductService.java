package com.foodapi.food_service.service;

import com.foodapi.food_service.model.ProductModel;

import com.foodapi.food_service.repo.ProductRepo;
//inject repository into service


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ProductService implements ProductServiceRepo{

    private ProductRepo productRepository;

    @Autowired
    public ProductService(ProductRepo productRepository) {
        this.productRepository = productRepository;
    }

    public ProductModel getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductModel createProduct(ProductModel product) {
        // Additional logic/validation can be implemented here
        return productRepository.save(product);
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
}

