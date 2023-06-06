package com.foodapi.food_service.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Entity
@Table(name = "Customer")
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column (name = "name")
    private String name;

    @Column (name = "contact_no")
    private String contact_no;

   @Column (name = "email")
    private String email;

    //Default Constructor
    public CustomerModel() {

    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Parameterised Constructor
    public CustomerModel(int customerId, String name, String contact_no, String email){

        this.customerId = customerId;
        this.name = name;
        this.contact_no = contact_no;
        this.email = email;

    }

}
