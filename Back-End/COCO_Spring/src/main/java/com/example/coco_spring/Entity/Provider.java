package com.example.coco_spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long providerId;
    private String firstName;
    private String lastName;
    private long phoneNumber;



     @OneToOne(cascade = CascadeType.ALL)
    ProviderLocation providerLocation;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
            @JsonIgnore
    List<Delivery> deliveries;
    @OneToOne(cascade = CascadeType.ALL)
    Order order;

}