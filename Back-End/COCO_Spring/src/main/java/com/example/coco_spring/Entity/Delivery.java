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


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "deliveryman_id")
    @JsonIgnore
    Provider provider;

    @Enumerated(EnumType.STRING)
    private Status statut;

    @Enumerated(EnumType.STRING)
    private DeliveryOption deliveryOption;

    @OneToOne
    TimeSlot timeSlot;

    private boolean cancelled;

    @JsonIgnore
    @OneToMany(mappedBy = "delivery",cascade = CascadeType.ALL)
    List<Order> orders;

    public Delivery() {
        this.statut = Status.PENDING;
    }
}