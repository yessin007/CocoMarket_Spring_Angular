package com.example.coco_spring.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
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
    @NotBlank
    @Size(min = 3, max = 50)
    private String catalogName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String catalogDescription;

    @NotBlank
    @Past
    private Date date;



    @JsonIgnore
    @OneToOne(mappedBy = "storeCatalog")
    private Store store;

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

}