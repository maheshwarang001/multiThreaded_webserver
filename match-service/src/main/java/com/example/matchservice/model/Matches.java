package com.example.matchservice.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Matches {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID matchId;

    @Column(nullable = false)
    private int hashcode;

    @Column(nullable = false)
    private UUID user1;

    @Column(nullable = false)
    private UUID user2;

    @Column(nullable = false)
    private LocalDate date;
}
