package com.foodapi.food_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//lombok
@Builder //lombok
@Entity
@AllArgsConstructor// for all default constructor
@NoArgsConstructor
public class CartModel {

    @Id// the id notation is based of a sequence
    @SequenceGenerator(
            name = "food_service_id_sequence",
            sequenceName = "food_service_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "food_service_id_sequence"
    )

//attributes
    private Integer cart_id;
    private String cart_details;
    private Integer product_id;
    private Integer customer_id;

}
