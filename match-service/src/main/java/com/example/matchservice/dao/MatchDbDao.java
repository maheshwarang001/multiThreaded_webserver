package com.example.matchservice.dao;

import java.util.UUID;

public interface MatchDbDao {

    boolean matchHashCodeExists(int hashcode);

    void createMatch(UUID user1 , UUID user2, int hash);

    void deleteMatch(UUID matchID);
}
