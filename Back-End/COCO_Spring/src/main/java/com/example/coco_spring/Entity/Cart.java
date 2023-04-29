package com.example.coco_spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table( name = "Carts")
public class Cart implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long cartId;
    private Long productQuantity;

    @OneToOne
    private Product productAngluar;
    @OneToMany(cascade = CascadeType.ALL)
    List<Product> products;

    @JsonIgnore
   @OneToOne(mappedBy = "cart")
    User user;
    @JsonIgnore
    @OneToOne
    Order order;
    @OneToOne
    User userAngular;

   /* @JsonIgnore
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>(); */

     /* Yessin Khlif */
    /*@JsonIgnore
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    List<Product> products = new ArrayList<>();*/

    public Cart(Product productAngluar, User userAngular) {
        this.productAngluar = productAngluar;
        this.user = userAngular;
    }

    public Cart() {

    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Product getProductAngluar() {
        return productAngluar;
    }

    public void setProductAngluar(Product productAngluar) {
        this.productAngluar = productAngluar;
    }

    public User getUser() {
        return userAngular;
    }

    public void setUser(User userAngular) {
        this.user = userAngular;
    }
}
