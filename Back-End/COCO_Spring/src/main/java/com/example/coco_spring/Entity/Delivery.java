package com.example.coco_spring.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deliveryId;

    private Double priceDelivery;
    private String clientLocation;
    private String signature;
    @Temporal (TemporalType.DATE)
    private Date creationDate;
    @Enumerated(EnumType.STRING)
    private Status statut;
    @Enumerated(EnumType.STRING)
    private Option option;
}
