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
@Table( name = "StoreCatalog")
public class StoreCatalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catalogId;

    private String catalogName;

    private String catalogDescription;

    private Date date;



    @JsonIgnore
    @OneToOne(mappedBy = "storeCatalog")
    private Store store;

}