package com.example.matchservice.service;

import com.example.matchservice.entity.MatchDBImpl;
import com.example.matchservice.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
public class MatchService {

    @Autowired
    private MatchDBImpl matchDB;

    @Autowired
    private Utils utils;

    @Autowired
    private RedisTemplate redisTemplate;


    public boolean matchCreater(UUID user1, UUID user2){

        if(matchExist(user1,user2) == true){
            System.out.println("Match exist DB");
            return true;
        }
        else{
            return redisCallBack(user1,user2);
        }
    }

    private boolean matchExist(UUID user1, UUID user2) {

        int hash = utils.consistentHashcode(user1, user2);

        return matchDB.matchHashCodeExists(hash);
    }

    private void isMatch(UUID user1, UUID user2) {
        int hash = utils.consistentHashcode(user1, user2);
        matchDB.createMatch(user1, user2, hash);
    }

    private boolean redisCallBack(UUID user1, UUID user2) {
        int hash = utils.consistentHashcode(user1, user2);

        if (redisTemplate.opsForValue().get(String.valueOf(hash)) != null) {
            try {

                System.out.println("its a match");
                redisTemplate.delete(String.valueOf(hash));

                Thread thread = new Thread(() -> isMatch(user1, user2));
                thread.start();

                return true;

            } catch (Exception e) {
                throw e;
            }

        } else {
            System.out.println("Someone swapped");
            try {
                redisTemplate.opsForValue().set(String.valueOf(hash), LocalDate.now().toString());

            }catch (Exception e){
                throw e;
            }
        }

        return false;
    }


}
