package com.example.recommendationservice.model;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRecommend {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String image;
    private String preferenceGender;
    private String city;
    private String country;


}
