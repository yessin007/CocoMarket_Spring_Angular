package com.example.coco_spring.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaleDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idd;
    private String productName;
    private Long quantity;


    public ProductSaleDTO(Long productId, String productName, String description, float price, ProductCategory productCategory) {

    }
}
