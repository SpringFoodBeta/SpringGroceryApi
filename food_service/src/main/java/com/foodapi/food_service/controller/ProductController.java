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

import com.foodapi.food_service.model.ProductModel;
import com.foodapi.food_service.response.ResponseHandler;
import com.foodapi.food_service.service.ProductServiceImp;
import com.foodapi.food_service.service.ProductService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;


//inject service into controller

@RestController
@RequestMapping("/api/products")

public class ProductController {

    private final ProductServiceImp productServiceImp;

    @Autowired //This allows the controller to use the methods provided by the service.
    public ProductController(ProductServiceImp productServiceImp) {
        this.productServiceImp = productServiceImp;
    }

    //GET all products - (view all products)
    @GetMapping(value = "/getAllProducts")
    public ResponseEntity<Object> getAllProducts() {
        try {
            List<ProductModel> products = productServiceImp.getAllProducts();
            if (products.size() > 0){
                return ResponseHandler.generateResponse("Successfully retrieved products!", HttpStatus.OK, products);
            } else {
                return ResponseHandler.generateResponse("No products found! Add a new product!", HttpStatus.NOT_FOUND, products);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    // GET product by id - (view one by fetching it with its ID)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        try {
            ProductModel product = productServiceImp.getProductById(id);
            return ResponseHandler.generateResponse("Successfully retrieved product!", HttpStatus.OK, product);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }


    @PostMapping(value = "/addProducts")
    public ResponseEntity<Object> createProduct(@Validated @NonNull @RequestBody(required = false) ProductModel product) {
        try {
            if (product == null) {
                throw new IllegalArgumentException("Product details are missing in the request body.");
            }
            // Perform name validation
            if (product.getName() == null || product.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Required!!! Name field cannot be empty!");
            }
            // Perform description validation
            if (product.getDescription() == null || product.getDescription().trim().isEmpty()) {
                throw new IllegalArgumentException("Required!!! Description field cannot be empty!");
            }
            // Perform category validation
            if (product.getCategory() == null ||
                    product.getCategory().getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Required!!! Category field cannot be empty");
            }
            // Perform price validation
            if (Objects.isNull(product.getPrice()) ) {
                throw new IllegalArgumentException("Required!!! Price field cannot be empty!");
            }

            if (Objects.isNull(product.getImageUrl()) ) {
                throw new IllegalArgumentException("Required!!! image field cannot be empty!");
            }


            // Validate that the price attribute is a double
//            if (product.getPrice().getClass() != Double.class) {
//                throw new IllegalArgumentException("Price must be a valid double value.");
//            }

            // Type validation and conversion for the price attribute
            double price = (Double) product.getPrice();

            // Set the converted price value back to the product object
            product.setPrice(price);

            ProductModel prod = productServiceImp.createProduct(product);
            if (prod != null) {
                return ResponseHandler.generateResponse("Successfully added product!", HttpStatus.OK, prod);
            } else {
                return ResponseHandler.generateResponse("Failed to add product.", HttpStatus.BAD_REQUEST, prod);
            }
        } catch (IllegalArgumentException e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    // PUT - (update a product by an ID)
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable("id") Long id, @RequestBody ProductModel product) {
        ProductModel updatedProduct = productServiceImp.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE - (delete a product by an ID)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productServiceImp.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // SEARCH and FILTERING
    @GetMapping("/search")
    public ResponseEntity<Object> findByCategory(@RequestParam(required = false) String productName,
                                                             @RequestParam(required = false) String categoryName) {
        try {
            List<ProductModel> searchResults = productServiceImp.findByCategory(productName, categoryName);

            if (searchResults.isEmpty()) {
                return ResponseHandler.generateResponse("Not Found!!!", HttpStatus.NOT_FOUND, null);
            }
            return ResponseHandler.generateResponse("Search Found!!!", HttpStatus.OK, searchResults);
            //return ResponseEntity.ok(products);
        } catch (Exception e) {
            // Handle the exception and return an appropriate response
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }


    // Constructor injection

    // POST end point to add the image to the database
    @PostMapping("/addImage")
    public String saveImage(@RequestBody ProductModel image){
        return productServiceImp.saveImage(image);
    }

    // POST endPoint to upload the images to cloudinary
    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("image") MultipartFile file,
                              ProductModel image){

        productServiceImp.uploadFile(image, file);
        return "image uploaded successfully";
    }

}





