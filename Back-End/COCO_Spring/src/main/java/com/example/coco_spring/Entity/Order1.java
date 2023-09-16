package com.example.coco_spring.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table( name = "Order1")
public class Order1 implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long orderId;


    private String FirstName;
    private String LastName;
    private String Phone;
    private String Address;

    private String EmailAddress;
    private String Country;
    private String City;
    private String PostalCode;
    private String PaymentMethod;
    private Float amountBill;
    private String OrderStatus;








    //@OneToOne
    //Payement payement;
    @JsonIgnore
    @OneToOne
    Payement payement;
    @JsonIgnore
    @OneToOne(mappedBy = "order")
    Cart cart ;
}