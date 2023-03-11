package com.example.coco_spring.Entity;

import lombok.Getter;
import lombok.*;
import lombok.experimental.*;

import javax.persistence.*;
import java.io.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class Quiz implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long quizId;

    String theme;

    @OneToMany(cascade = CascadeType.ALL)
    Set<QuizQuestion> questions; // Questions related to quizz
}