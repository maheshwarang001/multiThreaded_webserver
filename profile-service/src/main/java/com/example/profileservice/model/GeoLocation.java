package com.example.profileservice.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class GeoLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID geolocationId;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

}
