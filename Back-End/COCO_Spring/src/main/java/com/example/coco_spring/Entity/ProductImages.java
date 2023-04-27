package com.example.coco_spring.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    private String name;
    private String type;

    private String preview;

    @Column(length = 50000000)
    private byte[] picByte;

    public ProductImages(String originalFilename, String contentType, byte[] bytes) {
        this.name=originalFilename;
        this.type=contentType;
        this.picByte=bytes;
    }
}
