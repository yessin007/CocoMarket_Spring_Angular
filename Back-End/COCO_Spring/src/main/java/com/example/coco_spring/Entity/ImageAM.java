package com.example.coco_spring.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name="images_AM")
public class ImageAM {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String name ;
    private String type ;
    @Column(length = 50000000)
    private byte[] picByte;
    public ImageAM(String originalFilename, String contentType, byte[] bytes) {
        this.name=originalFilename;
        this.type=contentType;
        this.picByte=bytes;
    }


}
