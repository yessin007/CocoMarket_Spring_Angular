package com.example.coco_spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class PostStore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long postId;

    String postTitle;

    Date createdAt;

    String body;

     String imagenposte;

    int nb_Signal;

    int nb_etoil;
    @ManyToOne
    User user;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postStore")
    Set<PostLike> postLikes;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postStore")
    Set<PostDislike> postDislikes;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postStore")
    Set<PostComment> postComments;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    Set<User> reportedby;
}
