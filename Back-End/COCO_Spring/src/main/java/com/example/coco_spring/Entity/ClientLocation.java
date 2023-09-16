package com.example.coco_spring.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ClientLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String address;
    private double latitude;
    private double longitude;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "clientLocation")
    Delivery delivery;
}
