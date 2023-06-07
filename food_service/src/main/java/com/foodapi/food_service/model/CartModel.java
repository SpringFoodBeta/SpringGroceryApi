package com.foodapi.food_service.model;

import jakarta.persistence.*;

@Entity
@Table(name= "Cart")
class CartModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cart_id;

    @Column(name = "cart_details")
    private String cart_details;

    @JoinColumn(name = "product_id")
    private ProductModel product;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    public CartModel() {
    }

    public CartModel(Long cart_id, String cart_details, ProductModel product, CustomerModel customer) {
        this.cart_id = cart_id;
        this.cart_details = cart_details;
        this.product = product;
        this.customer = customer;
    }


    public Long getCart_id() {
        return cart_id;
    }

    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }

    public String getCart_details() {
        return cart_details;
    }

    public void setCart_details(String cart_details) {
        this.cart_details = cart_details;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
}




