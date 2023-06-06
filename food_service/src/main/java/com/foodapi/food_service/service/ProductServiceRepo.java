package com.foodapi.food_service.service;

import com.foodapi.food_service.model.ProductModel;
import com.foodapi.food_service.repo.ProductRepo;

import java.util.List;

public interface ProductServiceRepo {

     //declaration of functions
     List<ProductModel> getAllProducts();
     ProductModel getProductById(Long id);
     ProductModel updateProduct(Long id, ProductModel product);

     ProductModel createProduct(ProductModel product);


     List<ProductModel> findByCategory(String productName, String categoryName);

}
