package com.example.coco_spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

// Delivery.java
@Entity
@Getter
@Setter
@Table(name = "deliveries")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_address")
    private String clientAddress;

    @Column(name = "client_latitude")
    private Double clientLatitude;

    @Column(name = "client_longitude")
    private Double clientLongitude;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "deliveryman_id")
    Provider provider;
    @Enumerated(EnumType.STRING)
    private Status statut;
    @Enumerated(EnumType.STRING)
    private DeliveryOption deliveryOption;
    @JsonIgnore
    @ManyToOne
    Provider provider;
    @OneToOne
    TimeSlot timeSlot;



    @JsonIgnore
    @OneToMany(mappedBy = "delivery",cascade = CascadeType.ALL)
    List<Order> orders;


}
