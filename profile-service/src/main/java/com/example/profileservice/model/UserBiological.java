package com.example.profileservice.model;

import com.example.profileservice.data.Sex;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Data
@Entity
public class UserBiological {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userBioId;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String sex;

}
