package com.example.coco_spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class PostLike implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long postLikeId;

    Date likedAt;

    Boolean isLiked ;

    @JsonIgnore
    @ManyToOne
    User user; // The user who clicked Like

    @JsonIgnore
    @ManyToOne
    PostStore postStore; // The post to like
}
