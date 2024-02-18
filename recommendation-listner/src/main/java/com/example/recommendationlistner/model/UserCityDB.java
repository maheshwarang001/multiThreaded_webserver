package com.example.recommendationlistner.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
