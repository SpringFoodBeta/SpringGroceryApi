package com.foodapi.food_service.service;

import com.foodapi.food_service.model.ProductModel;
import com.foodapi.food_service.repo.ProductRepo;

public interface ProductServiceRepo {

    public ProductModel getProductById(Long id);
    public ProductModel updateProduct(Long id, ProductModel product);

    public ProductModel createProduct(ProductModel product);
}
