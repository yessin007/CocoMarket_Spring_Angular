package com.example.coco_spring.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @Temporal (TemporalType.DATE)
    private Date estimationDate;
    @Enumerated(EnumType.STRING)
    private ProviderRate providerRate;
    @OneToMany(mappedBy = "provider",cascade = CascadeType.ALL)
    List<Delivery> deliveries;
}
