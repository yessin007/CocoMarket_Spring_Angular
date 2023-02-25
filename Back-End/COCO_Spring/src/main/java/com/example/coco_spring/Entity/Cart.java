package com.example.coco_spring.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
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
    @OneToMany(cascade = CascadeType.ALL)
    List<Product> products;
    @OneToOne(mappedBy = "cart")
    User user;
    @OneToOne
    Order order;

}
