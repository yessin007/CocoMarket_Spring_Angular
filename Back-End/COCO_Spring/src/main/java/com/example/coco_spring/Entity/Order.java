package com.example.coco_spring.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table( name = "Orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long cartId;
    private String productReference;

    @Temporal (TemporalType.DATE)
    private Date orderDate;
    private String orderStatus;
    private String orderAddress;
    private Float amountBill;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "delivery_id")
    Delivery delivery;
    @JsonIgnore
    @OneToOne
    Payement payement;
    @OneToOne(mappedBy = "order")
    Cart cart ;
}
