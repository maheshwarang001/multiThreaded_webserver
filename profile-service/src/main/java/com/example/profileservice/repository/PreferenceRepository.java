package com.example.profileservice.repository;


import com.example.profileservice.model.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PreferenceRepository extends JpaRepository<Preferences, UUID> {

    Preferences findByPrefId(UUID id);

}
