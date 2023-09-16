package com.example.coco_spring.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    private String reviewTitle;
    private String reviewText;
    private boolean verified;
    private float rating;
    @Column(name = "created_at", nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @ManyToOne
    @JsonIgnore
    User user;
    @ManyToOne
    @JsonIgnore
    Product product;
    @JsonIgnore
    @OneToMany(mappedBy = "review")
    List<LikeDislikeProduct> likeDislikeProducts;

}
