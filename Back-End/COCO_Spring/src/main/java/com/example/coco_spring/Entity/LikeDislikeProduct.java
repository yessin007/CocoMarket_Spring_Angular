package com.example.coco_spring.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeDislikeProduct
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    ProductRate productRate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    User user;
    @ManyToOne
    @JsonIgnore
    Product product;
    @ManyToOne
    @JsonIgnore
    Review review;
}
