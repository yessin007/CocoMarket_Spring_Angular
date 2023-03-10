package com.example.coco_spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long postCommentId;

    String commentBody;

    Date commentedAt;


    @ManyToOne
    User user; // The user who wants to comment

    @JsonIgnore
    @ManyToOne
    PostStore postStore; // The post to comment



}
