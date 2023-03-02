package com.example.coco_spring.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
    private float discount;
    private int yearsOfWarranty;
    @Enumerated(EnumType.STRING)
    ProductCategory productCategory;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    List<Review> reviews;
    @JsonIgnore
    @ManyToMany(mappedBy = "products",cascade = CascadeType.ALL)
    List<Store> stores;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    List<LikeDislikeProduct> likeDislikeProducts;


    public List<Cart> getCarts() {
        List<Cart> carts = new ArrayList<>();
        for (Order order : this.getOrders()) {
            Cart cart = order.getCart();
            if (cart != null && !carts.contains(cart)) {
                carts.add(cart);
            }
        }
        return carts;
    }

}
