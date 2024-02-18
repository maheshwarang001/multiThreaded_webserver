package com.example.recommendationservice.model;


import lombok.Data;

import java.util.UUID;

@Data
public class Metadata {

    private UUID uuid;
    private String firstname;
    private String lastname;
    private String gender;
    private int age;
    private String usersex;
    private String city;
    private String country;
    private String userpreference;

}
