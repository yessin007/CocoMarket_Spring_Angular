package com.example.coco_spring.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
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
    @NotBlank
    @Size(min = 3, max = 50)
    private String catalogName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String catalogDescription;

    @Past
    private Date date;



    @JsonIgnore
    @OneToOne(mappedBy = "storeCatalog")
    private Store store;

}