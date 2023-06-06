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

import com.foodapi.food_service.model.CategoryModel;
import com.foodapi.food_service.model.ProductModel;
import com.foodapi.food_service.service.ProductService;
import com.foodapi.food_service.service.ProductServiceRepo;
import lombok.NonNull;
import org.apache.http.client.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//inject service into controller

@RestController
@RequestMapping("/products")

public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable("id") Long id) {
        ProductModel product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // POST /products
    @PostMapping(value = "/addProducts")
//    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel product) {
//        ProductModel createdProduct = productService.createProduct(product);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
//    }
    public ProductModel createProduct(@Validated @NonNull @RequestBody ProductModel product)
    {
        return productService.createProduct(product);
    }

    // PUT /products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable("id") Long id, @RequestBody ProductModel product) {
        ProductModel updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE /products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


    //searching and filtering
    @GetMapping("/search")
    public List<ProductModel> findByCategory(@RequestParam(required = false) String productName,
                                                      @RequestParam(required = false) double price,
                                                      @RequestParam(required = false) String categoryName){
        List<ProductModel> products = productService.findByCategory(productName, price, categoryName);

        return products;
    }
}





