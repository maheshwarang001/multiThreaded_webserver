package com.example.profileservice.repository;

import com.example.profileservice.model.UserBiological;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserBiologicalRepository extends JpaRepository<UserBiological, UUID> {

    UserBiological findByUserBioId(UUID uid);
}
