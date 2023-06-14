package com.foodapi.food_service.model;

import java.sql.Date;

public class OrderModel {

    private String quantity;

    private Date dateCreated;

    private Double totalPrice;

    public OrderModel(String quantity, Date dateCreated, Double totalPrice) {
        this.quantity = quantity;
        this.dateCreated = dateCreated;
        this.totalPrice = totalPrice;
    }

    public OrderModel() {
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
