package com.example.coco_spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class Media implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long mediaId;
	
	
	
	private String name;
	
    private String imagenUrl;
    
    private String codeImage;

    
	@JsonIgnore
    @ManyToOne
    Store store;
   
    
    @JsonIgnore
    @ManyToOne
    PostStore post;
    

    
	public Media(String name, String imagenUrl, String imagencode) {
	
		this.name = name;
		this.imagenUrl = imagenUrl;
		this.codeImage = imagencode;
	}
    
    
    
    
    
    

}
