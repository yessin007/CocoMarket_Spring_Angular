package com.example.coco_spring.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class CategoryAdve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idCategoryAdv;

    String nameCategory;
}
