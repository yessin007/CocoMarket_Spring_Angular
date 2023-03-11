package com.example.coco_spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table( name = "Carts")
public class Cart implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long cartId;
    private Long productQuantity;
   /*
    @OneToMany(cascade = CascadeType.ALL)
    List<Product> products;
    */
    @JsonIgnore
   @OneToOne(mappedBy = "cart")
    User user;
    @JsonIgnore
    @OneToOne
    Order order;
@JsonIgnore
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

}
