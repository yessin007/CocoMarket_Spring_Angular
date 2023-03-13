package com.example.coco_spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "AfterSaleServices")
public class AfterSaleServices implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="serviceId")
    private Long serviceId;
    private String description ;
    @JsonIgnore
    @ManyToOne
    Store store ;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "afterSaleServices")
    private List<CustomerFeedback> customerFeedbacks;


}
