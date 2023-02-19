package com.example.coco_spring.payload;

import lombok.Data;

import java.util.Date;

@Data
public class SignUpDto {
    private String name;
    private String LastName;
    private String username;
    private String email;
    private String password;
    private String Address;
    private Date DayOfBirth;
    private String Cin;
    private String TelNum;
    private Long roleId;
}