package com.example.profileservice.model;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;


@Data
public class RecommendationModel{

    private String userId;
    private String city;
    private String country;

}
