package com.foodapi.food_service.repo;

import com.foodapi.food_service.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<CategoryModel, Long> {
    List<CategoryModel> findByNameContaining(String keyword);
}
