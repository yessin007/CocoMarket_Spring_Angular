package com.example.coco_spring.Entity;

import com.google.common.util.concurrent.ServiceManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.service.Service;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class CustomerFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcus")
    private Long idcus;
    private String comment;
    private int score;
    @ManyToOne
    private AfterSaleServices afterSaleServices;

}
