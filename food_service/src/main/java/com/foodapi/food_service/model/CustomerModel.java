package com.foodapi.food_service.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
@Builder
@Entity
@Table(name = "Customer")
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column (name = "firstName")
    private @NotBlank String firstName;

    @Column (name = "lastName")
    private @NotBlank String lastName;

    @Column (name = "email")
    private @NotBlank String email;

    @Column (name = "contactNo")
    private @NotBlank String contactNo;

    @Min(8)
    @Max(32)
    @Column (name = "password")
    private @NotBlank String password;

    @Column (name = "address")
    private @NotBlank String address;

    //Default Constructor
    public CustomerModel() {

    }

    //Parameterised Constructor
    public CustomerModel(@NotBlank int customerId, @NotBlank String firstName,@NotBlank String lastName,@NotBlank String email, @NotBlank String contactNo, @NotBlank String password, @NotBlank String address) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNo = contactNo;
        this.password = password;
        this.address = address;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
