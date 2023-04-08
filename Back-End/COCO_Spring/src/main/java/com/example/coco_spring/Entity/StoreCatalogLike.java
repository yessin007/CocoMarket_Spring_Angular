package com.example.coco_spring.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class StoreCatalogLike implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long storeCatalogLikeId;

    LocalDate likedAt;

    Boolean isLiked ;

    @ManyToOne
    User user; //the user who clicked like

    @JsonIgnore
    @ManyToOne
    StoreCatalog storeCatalog; //the post to like
}
