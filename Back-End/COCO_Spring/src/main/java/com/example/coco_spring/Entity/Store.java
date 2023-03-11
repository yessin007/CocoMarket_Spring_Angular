package com.example.coco_spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Store")
public class Store implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="storeId")
    private Long storeId;
    private  String storeName ;
    private String storeLocation;
    private Integer contactInformation ;
    private String storeDescription ;
    @Enumerated(EnumType.STRING)
    private Category category ;
    private String storeEmailAddress ;

    private String link;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="store" )
    private Set<AfterSaleServices> afterSaleServicess;
    @ManyToMany
    List<Product> products;


    @JsonIgnore
    @OneToOne
    private StoreCatalog storeCatalog;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<Quiz> quiz; // Quizzes related to course (Unidirectionnelel)
}
