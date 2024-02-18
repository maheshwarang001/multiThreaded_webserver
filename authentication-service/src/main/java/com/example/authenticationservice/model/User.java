package com.example.authenticationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;

import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;


    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String pwd;

}
