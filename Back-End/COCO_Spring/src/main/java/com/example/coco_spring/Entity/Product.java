package com.example.coco_spring.Entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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
    @ManyToMany(mappedBy = "products",cascade = CascadeType.ALL)
    List<Store> stores;
    

}
