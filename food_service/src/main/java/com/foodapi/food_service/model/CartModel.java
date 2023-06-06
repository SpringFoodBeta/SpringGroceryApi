package com.foodapi.food_service.model;

import jakarta.persistence.*;


@Table(name= "Cart")

public class CartModel {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)

        @Column(name = "cartId")
        private Long cartId;

        @Column(name = "cartDetails")
        private String cartDetails;

        @JoinColumn(name = "productId")
        private Long productId;

        @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinColumn(name = "customerId")
        private Long customerId;

        public CartModel() {

        }

        public CartModel(Long cartId, String cartDetails, Long productId, Long customerId){
            this.cartId = cartId;
            this.cartDetails = cartDetails;
            this.productId = productId;
            this.customerId = customerId;

        }

    }
