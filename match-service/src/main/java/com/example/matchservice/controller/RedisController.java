package com.example.matchservice.controller;

import com.example.matchservice.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/match")
public class RedisController {

    @Autowired
    MatchService service;

    @PostMapping("/check")
    public boolean checkRedis(@RequestParam(value = "u1") String user1, @RequestParam("u2") String user2){
        return service.matchCreater(UUID.fromString(user1),UUID.fromString(user2));
    }
}
