package com.example.coco_spring.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deliveryId;
    private Double priceDelivery;
    private String clientLocation;
    private String signature;
    private Date creationDate;
    private Status statut;
    private Option option;
}
