package com.example.coco_spring.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class CustomerFeedback {
    @Id

    private Long idcus;
    private String comment;
    private int score;

}
