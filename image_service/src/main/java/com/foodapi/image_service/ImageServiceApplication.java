package com.foodapi.image_service;

import com.foodapi.image_service.service.ImageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ImageServiceApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(ImageServiceApplication.class,args);
        System.out.println("Running on port 8080");
    }
}
