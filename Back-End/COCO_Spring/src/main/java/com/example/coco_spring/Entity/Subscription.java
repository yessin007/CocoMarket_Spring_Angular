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
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;

    @ManyToOne
    User user;

    @Temporal(TemporalType.DATE)
    Date dateOfSubCreation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subbedproduct")
    Product product;

    int subMonths;

    int remainingDaysINMonth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prize")
    Product prize;

}