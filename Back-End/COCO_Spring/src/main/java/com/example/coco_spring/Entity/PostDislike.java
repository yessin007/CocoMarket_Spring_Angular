package com.example.coco_spring.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class PostDislike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long postDislikeId;

    Date dislikedAt;

    @ManyToOne
    User user; // The user who clicked Like

    @ManyToOne
    PostStore postStore; // The post to like
}