package com.example.profileservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.UUID;


@Data
@Entity
public class UserDetail {

    @Id
    @Column(nullable = false)
    private UUID userId;


    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


    @OneToOne(targetEntity = UserBiological.class)
    private UserBiological biological;

    @OneToOne(targetEntity = GeoLocation.class)
    private GeoLocation geoLocation;

    @OneToOne(targetEntity = Preferences.class)
    private Preferences preference;
}
