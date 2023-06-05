package com.foodapi.food_service.service;

import com.foodapi.food_service.model.ProductModel;
import com.foodapi.food_service.repo.ProductRepo;

public interface ProductServiceRepo {

     ProductModel getProductById(Long id);
     ProductModel updateProduct(Long id, ProductModel product);

     ProductModel createProduct(ProductModel product);
}
