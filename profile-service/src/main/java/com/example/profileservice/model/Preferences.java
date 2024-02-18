package com.example.profileservice.model;

import com.example.profileservice.data.Preference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Preferences {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID prefId;

    @Column(nullable = false)
    private String preference;
}
