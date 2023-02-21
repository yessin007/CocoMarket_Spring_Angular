package com.example.coco_spring.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Announce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long announceId;
    private String announceTitle;

    @Lob
    private byte[] images;

    private Date announceStartDate;
    private Date announceExpiryDate;
    private String targetAudience;
    private int views;
    private int clicks;
}
