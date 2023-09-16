package com.example.coco_spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    ClientLocation clientLocation;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
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
    @JsonProperty("firstName")
    public String getProviderName() {
        return provider != null ? provider.getFirstName() : null;
    }
    @JsonProperty("lastName")
    public String getProviderLastName() {
        return provider != null ? provider.getLastName() : null;
    }
    public Delivery() {
        this.statut = Status.PENDING;
    }
}