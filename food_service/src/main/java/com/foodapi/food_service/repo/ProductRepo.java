package com.foodapi.food_service.repo;

import com.foodapi.food_service.model.CategoryModel;
import com.foodapi.food_service.model.ProductModel;
import jakarta.persistence.criteria.*;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;
import java.util.List;

public interface ProductRepo extends JpaRepository<ProductModel,Long >{


}
