package com.example.coco_spring.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table( name = "Payements")
public class Payement implements Serializable{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long payementId;
    @Temporal (TemporalType.DATE)
    private Date payementDate;
    private String payementMethod;
    private String bankTransferStatus;
    private String transactionStatus;
}
