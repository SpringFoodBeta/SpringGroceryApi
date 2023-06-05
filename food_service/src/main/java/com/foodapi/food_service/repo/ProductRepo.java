package com.foodapi.food_service.repo;

import com.foodapi.food_service.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductModel,Long > {

}
