package com.example.matchservice.entity;

import com.example.matchservice.dao.MatchDbDao;
import com.example.matchservice.model.Matches;
import com.example.matchservice.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Component
public class MatchDBImpl implements MatchDbDao {

    @Autowired
    MatchRepository matchRepository;

    @Override
    public boolean matchHashCodeExists(int hashcode) {
      return matchRepository.existsByHashcode(hashcode);
    }

    @Override
    public void createMatch(UUID user1, UUID user2, int hash) {

        Matches matches = new Matches();
        matches.setHashcode(hash);
        matches.setUser1(user1);
        matches.setUser2(user2);
        matches.setDate(LocalDate.now());


        matchRepository.save(matches);
    }

    @Override
    public void deleteMatch(UUID matchID) {
        matchRepository.deleteByMatchId(matchID);
    }
}
