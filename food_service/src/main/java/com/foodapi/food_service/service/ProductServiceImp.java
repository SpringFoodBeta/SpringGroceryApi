package com.foodapi.food_service.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.foodapi.food_service.exception.ProductAPIRequestException;
import com.foodapi.food_service.model.ProductModel;

import com.foodapi.food_service.repo.ProductRepo;
//inject repository into service


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//write the business logic
//definition of our functions

@Service
public class ProductServiceImp implements ProductService {

    @Autowired  //This allows the service to interact with the database through the repository.
    private final ProductRepo productRepository;
    private final Cloudinary cloudinary;

    public ProductServiceImp(ProductRepo productRepository, Cloudinary cloudinary) {
        this.productRepository = productRepository;
        this.cloudinary = cloudinary;
    }

    public List<ProductModel> getAllProducts() {
        List<ProductModel> products = new ArrayList<ProductModel>();
        productRepository.findAll()
                .forEach(product -> products.add(product));
        return products;
    }

    public ProductModel getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductModel createProduct(ProductModel product) {


        // Additional logic/validation can be implemented here

        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return productRepository.save(product) ;
    }

    public ProductModel updateProduct(Long id, ProductModel product) {
        ProductModel existingProduct = getProductById(id);
        // Update the attributes of the existingProduct with the attributes from the product
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setImageUrl(product.getImageUrl());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        ProductModel product = getProductById(id);
        productRepository.delete(product);
    }

    //search and filter
    public List<ProductModel> findByCategory(String productName,  String categoryName) {
        try {
            return productRepository.findByCategory(productName, categoryName);
        }catch(Exception ex)
        {
            throw new ProductAPIRequestException("Error while retrieving products by category:" + ex.getMessage());
        }

    }



    // function to upload image to cloudinary

    public void uploadFile(ProductModel image, MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            image.setImageUrl(uploadResult.get("url").toString());
            productRepository.save(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // This is the function to save the image to the database
    public String saveImage(ProductModel image){
        productRepository.save(image);
        return "Successfully added image";
    }

}

