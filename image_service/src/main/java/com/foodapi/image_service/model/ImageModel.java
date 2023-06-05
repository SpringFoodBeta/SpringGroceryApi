package com.foodapi.image_service.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Image")
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long imageId;

    private String imageURL;

    private ImageModel(Long imageId, String imageURL)
    {
        this.imageId =imageId;
        this.imageURL=imageURL;
    }


    //get image id
    public Long getImageId()
    {
        return imageId;
    }


    //setImageId
    public void setImageId(Long imageId)
    {
        this.imageId=imageId;
    }

    public String getImageURL()
    {
        return imageURL;
    }



    //setImageURL

    public void setImageURL(String imageURL)
    {
        this.imageURL=imageURL;
    }
}
