package com.example.recommendationlistner.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class RecommendationModel{

    private String userId;
    private String city;
    private String country;

}
