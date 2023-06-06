package com.foodapi.food_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class FoodServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(FoodServiceApplication.class,args);
        System.out.println("Server is running on port : 8081");
    }
}

