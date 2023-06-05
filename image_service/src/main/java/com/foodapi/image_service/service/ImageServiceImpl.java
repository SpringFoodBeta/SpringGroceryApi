package com.foodapi.image_service.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.foodapi.image_service.model.ImageModel;
import com.foodapi.image_service.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private final ImageRepo imageRepo;
    private final Cloudinary cloudinary;

    // Constructor injection
    public ImageServiceImpl(ImageRepo imageRepo , Cloudinary cloudinary){
        this.imageRepo = imageRepo;
        this.cloudinary = cloudinary;
    }

    // function to upload image to cloudinary

    public void uploadFile(ImageModel image, MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            image.setImageURL(uploadResult.get("url").toString());
            imageRepo.save(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // This is the function to save the image to the database
    public String saveImage(ImageModel image){
        imageRepo.save(image);
        return "Successfully added image";
    }




}
