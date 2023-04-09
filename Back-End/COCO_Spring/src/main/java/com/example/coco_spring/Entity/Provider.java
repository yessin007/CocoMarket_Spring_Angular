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
    private String providerName;
    private long providerPrice;

    @JsonIgnore
    @OneToMany(mappedBy = "provider",cascade = CascadeType.ALL)
    List<Delivery> deliveries;

    @OneToOne
    ProviderLocation providerLocation;

    @OneToMany(mappedBy = "provider",cascade = CascadeType.ALL)
    private List<ProviderRating> providerRatings;


}