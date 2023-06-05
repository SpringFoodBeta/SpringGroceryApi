package com.foodapi.image_service.controller;


import com.foodapi.image_service.model.ImageModel;
import com.foodapi.image_service.service.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@RestController
@RequestMapping("image")
public class ImageController {

    @Autowired
    private final ImageServiceImpl imageService;

    // Constructor injection
    public ImageController(ImageServiceImpl imageService) {
        this.imageService = imageService;
    }

    // POST end point to add the image to the database
    @PostMapping("/addImage")
    public String saveImage(@RequestBody ImageModel image){
        return imageService.saveImage(image);
    }

    // POST endPoint to upload the images to cloudinary
    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("image") MultipartFile file, ImageModel image){

        imageService.uploadFile(image, file);
        return "image uploaded successfully";
    }
}
