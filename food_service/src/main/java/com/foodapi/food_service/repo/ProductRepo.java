package com.foodapi.food_service.repo;

import com.foodapi.food_service.model.CategoryModel;
import com.foodapi.food_service.model.ProductModel;
import jakarta.persistence.criteria.*;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;
import java.util.List;

public interface ProductRepo extends JpaRepository<ProductModel,Long>, JpaSpecificationExecutor<ProductModel> {
     default List<ProductModel> findByCategory(String productName, String categoryName){
        return findAll((new Specification<ProductModel>() {
            @Override
            public Predicate toPredicate(Root<ProductModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (productName != null) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + productName.toLowerCase() + "%"));
                }
                if (categoryName != null) {
                    Join<ProductModel, CategoryModel> prodCategoryJoin = root.join("category");
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(prodCategoryJoin.get("categoryName")), "%" + categoryName.toLowerCase() + "%" ));
                }

                return criteriaBuilder.and(predicates.toArray( new Predicate[predicates.size()]));
            }
        }));
    }
}
