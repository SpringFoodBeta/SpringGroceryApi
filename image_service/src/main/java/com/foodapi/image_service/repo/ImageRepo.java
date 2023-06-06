package com.foodapi.image_service.repo;

import com.foodapi.image_service.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<ImageModel,Long> {
}
