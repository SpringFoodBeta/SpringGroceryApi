package com.foodapi.food_service.controller;

//import com.foodapi.food_service.repo.ProductRepo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.foodapi.food_service.model.ProductModel;
//import com.foodapi.food_service.service.ProductService;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/products")
//public class ProductController {
//
//
//}
import com.foodapi.food_service.exception.ProductAPIRequestException;
import com.foodapi.food_service.model.CategoryModel;

import com.foodapi.food_service.model.ProductModel;
import com.foodapi.food_service.response.ResponseHandler;
import com.foodapi.food_service.service.ProductService;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//inject service into controller

@RestController
@RequestMapping("/api/products")

public class ProductController {

    private final ProductService productService;

    @Autowired //This allows the controller to use the methods provided by the service.
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping(value = "/getAllProducts")
//    public List<ProductModel> getAllProducts(){
//        return productService.getAllProducts();
//    }

    //GET all products - (view all products)
    @GetMapping(value = "/getAllProducts")
    public ResponseEntity<Object> Get() {
        try {
            List<ProductModel> products = productService.getAllProducts();
            if (products.size() > 0){
                return ResponseHandler.generateResponse("Successfully retrieved products!", HttpStatus.OK, products);
            } else {
                return ResponseHandler.generateResponse("No products found! Add a new product!", HttpStatus.OK, products);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Object>  findByCategory(@RequestParam(required = false) String productName,
                                                  @RequestParam(required = false) String categoryName){
        try {
            List<ProductModel> products = productService.findByCategory(productName, categoryName);
            if(productName != null && categoryName != null && !products.isEmpty())
            {
                return ResponseHandler.generateResponse("We have the product that you have mentioned", HttpStatus.OK, products);

            }else {
                return ResponseHandler.generateResponse("Error while retrieving products by category:", HttpStatus.OK, null);

            }
        }catch(Exception ex)
        {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.MULTI_STATUS, null);

        }

    }

    // GET product by id - (view one by fetching it with its ID)
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable("id") Long id) {
        ProductModel product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // POST product - (add a product to the database)
    @PostMapping(value = "/addProducts")
    public ProductModel createProduct(@Validated @NonNull @RequestBody ProductModel product)
    {
        return productService.createProduct(product);
    }

    // PUT - (update a product by an ID)
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable("id") Long id, @RequestBody ProductModel product) {
        ProductModel updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE - (delete a product by an ID)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    private static final Logger logger = LogManager.getLogger(ProductController.class);


    //searching and filtering
    // GET


}





