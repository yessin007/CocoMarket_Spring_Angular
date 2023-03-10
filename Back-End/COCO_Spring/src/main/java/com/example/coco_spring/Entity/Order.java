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
    @Temporal (TemporalType.DATE)
    private Date orderDate;
    private String orderStatus;
    private String orderAddress;
    private Float amountBill;
    private Long deliveryId;
    private Long payementId;

    @JsonIgnore
    @ManyToOne
    Delivery delivery;
    @JsonIgnore
    @OneToOne
    Payement payement;
    @OneToOne
    Cart cart ;
}
