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
import com.foodapi.food_service.exception.ResponseHandler;
import com.foodapi.food_service.model.CategoryModel;
import com.foodapi.food_service.model.ProductModel;
import com.foodapi.food_service.repo.ProductRepo;
import com.foodapi.food_service.service.ProductService;
import com.foodapi.food_service.service.ProductServiceRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

//inject service into controller

@RestController
@RequestMapping("/api/products")

public class ProductController {

    private ProductService productService;


    @Autowired //This allows the controller to use the methods provided by the service.
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //GET all products - (view all products)
    @GetMapping(value = "/getAllProducts")
    public List<ProductModel> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET product by id - (view one by fetching it with its ID)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") Long id) {
        try {
            ProductModel product = productService.getProductById(id);
            return ResponseHandler.generateResponse("Your data is retrieved data", HttpStatus.OK, product);
        }catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
//            throw new ProductAPIRequestException("Sorry dear cannot find that product");

        }


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

    //searching and filtering
    @GetMapping("/search")
    public List<ProductModel> findByCategory(@RequestParam(required = false) String productName,
                                                      @RequestParam(required = false) String categoryName){
        List<ProductModel> products = productService.findByCategory(productName, categoryName);

        return products;
    }
}





