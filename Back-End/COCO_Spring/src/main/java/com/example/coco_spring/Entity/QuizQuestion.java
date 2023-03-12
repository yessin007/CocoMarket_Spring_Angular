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
public class QuizQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long questionId;
    String question;
    int score;

    @OneToMany(cascade = CascadeType.ALL)
    Set<Answer> answers; // Answers related to questions
}