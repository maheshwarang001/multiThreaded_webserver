package com.example.matchservice.repository;

import com.example.matchservice.model.Matches;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MatchRepository extends JpaRepository<Matches, UUID> {

    boolean existsByHashcode(int hashcode);



    void deleteByMatchId(UUID id);


}
