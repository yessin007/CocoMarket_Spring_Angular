package com.example.coco_spring.Entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class BadWords implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long IdWord;


    String word;

}