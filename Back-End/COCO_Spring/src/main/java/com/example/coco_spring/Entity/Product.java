package com.example.coco_spring.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private String title;
    @Lob
    @Column(name = "CONTENT", length = 10000)
    private String description;
    private Long quantity;
    private String model;
    private String video;
    private Long stock;
    private String brand;
    private float price;
    @ElementCollection
    private List<String> collection;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<ProductImages> image;
    @Temporal(TemporalType.DATE)
    private Date dateOfPurchase;
    private float discount;
    private int yearsOfWarranty;
    @Column(name = "created_at", nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    ProductCategory productCategory;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    List<Review> reviews;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    Store storess;

    @JsonIgnore
    @ManyToMany(mappedBy = "products",cascade = CascadeType.ALL)
    List<Store> stores;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<LikeDislikeProduct> likeDislikeProducts;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    Cart cart;
    @OneToOne(mappedBy = "product")
    Subscription subscription;



}
