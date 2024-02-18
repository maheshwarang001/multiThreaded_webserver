package com.example.recommendationlistner.repository;

import com.example.recommendationlistner.model.UserCityDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecommendationRepository extends JpaRepository<UserCityDB, UUID> {

}
