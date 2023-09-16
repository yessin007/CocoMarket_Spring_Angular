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


    @OneToMany(mappedBy = "provider",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Delivery> deliveries;

    @OneToOne(cascade = CascadeType.ALL)
    ProviderLocation providerLocation;

    @OneToMany(mappedBy = "provider",cascade = CascadeType.ALL)
    private List<ProviderRating> providerRatings;


}