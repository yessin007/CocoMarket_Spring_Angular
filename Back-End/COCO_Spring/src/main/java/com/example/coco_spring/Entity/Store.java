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
    private Integer contactInformation ;
    @Lob
    @Column( length = 1000000)
    private String storeDescription ;
    @Enumerated(EnumType.STRING)
    private Category category ;
    private String storeEmailAddress ;
    @Lob
    private byte[] storeimg;
    private String link;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="store" )
    private Set<AfterSaleServices> afterSaleServicess;
    @ManyToMany
    List<Product> products;


    @OneToMany(mappedBy = "storess")
    List<Product> productList;




    private String storeLocations;


    @JsonIgnore
    @ManyToMany
    List<StoreCatalog>  storeCatalog;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<Quiz> quiz;
    @ManyToMany(fetch= FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinTable(name="store_images",
            joinColumns = {
                    @JoinColumn(name="store_id")
            },inverseJoinColumns = {
            @JoinColumn(name = "image_id")
    }
    )
    private Set<ImageSModel> storeImages;
}
