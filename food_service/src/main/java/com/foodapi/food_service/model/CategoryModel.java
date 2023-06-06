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

    //fetches the resource
    //cascade - if theres any update on parent table ,the child table is also updated
//    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    public List<ProductModel> product;

    public CategoryModel()
    {
        super();
    }



    public CategoryModel(long categoryId, String categoryName)
    {
        this.categoryId=categoryId;
        this.categoryName=categoryName;
    }

    //get category ID
    public long getCategoryId()
    {
        return categoryId;
    }
    //set category id
    public void setCategoryId(long categoryId)
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
