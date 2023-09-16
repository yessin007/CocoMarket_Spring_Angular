package com.example.coco_spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table( name = "StoreCatalog")
public class StoreCatalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catalogId;

    private String catalogName;


    private String catalogDescription;


    private Date date;
    @JsonIgnore
    @ManyToMany(mappedBy = "favories")
    List<User> users;

    @ManyToMany(fetch= FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinTable(name="storecatalog_images",
            joinColumns = {
                    @JoinColumn(name="catalog_id")
            },inverseJoinColumns = {
            @JoinColumn(name = "image_id")
    }
    )
    private Set<ImageAM> catalogImages;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    Store store;

    @JsonIgnore
    @ManyToMany(mappedBy = "storeCatalog",cascade = CascadeType.ALL)
    List<Store> stores;

}