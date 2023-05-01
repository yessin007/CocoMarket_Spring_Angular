package com.example.coco_spring.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSummary {
	@Id
	private long id;
	private String name;
	private String lastName;
	private String username;
	private String email;
	private String address;
	private Role roles;
	private String teNum;
	private boolean locked;
	private boolean expired;

	// constructor, getters, and setters
}
