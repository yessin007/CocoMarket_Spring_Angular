package com.example.coco_spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
            @JsonIgnore
    User user;

    @Temporal(TemporalType.DATE)
    Date dateOfSubCreation;
    @Temporal(TemporalType.DATE)
    Date dateEndOfSubscription;
    @OneToOne
    @JoinColumn(name = "subbedproduct")
            @JsonIgnore
    Product product;

    int subMonths;

    int remainingDaysINMonth;

    @OneToOne
    @JoinColumn(name = "prize")
            @JsonIgnore
    Product prize;

}