package com.foodapi.food_service.model;


import jakarta.persistence.*;

@Entity
@Table(name ="Category")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private Long categoryId;

    @Column(name= "categoryName")
    private String categoryName;


    public CategoryModel(Long categoryId, String categoryName)
    {
        this.categoryId=categoryId;
        this.categoryName=categoryName;
    }



    //get category ID
    public Long getCategoryId()
    {
        return categoryId;
    }
    //set category id
    public void setCategoryId(Long categoryId)
    {
        this.categoryId=categoryId;
    }
    //get category name
    public String getName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName =categoryName;
    }

}
