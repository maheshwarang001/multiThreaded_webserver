package com.example.recommendationservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class UserCityDB {

    @Id
    @Column(nullable = false)
    private UUID userId;
    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;
}