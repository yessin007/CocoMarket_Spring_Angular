package com.example.coco_spring.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String reference;
    private String productName;
    private String description;
    private Long quantity;
    @Lob
    private byte[] images;
    @Lob
    private byte[] model;
    @Lob
    private byte[] video;
    private String brand;
    private float price;
    @Temporal(TemporalType.DATE)
    private Date dateOfProduct;
    private float rating;
    private float discount;
    private int yearsOfWarranty;
    @Enumerated(EnumType.STRING)
    ProductCategory productCategory;
    @OneToMany(cascade = CascadeType.ALL)
    List<Review> reviews;

}
