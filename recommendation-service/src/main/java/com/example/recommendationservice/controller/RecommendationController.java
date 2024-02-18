package com.example.recommendationservice.controller;


import com.example.recommendationservice.model.UserRecommend;
import com.example.recommendationservice.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    @Autowired
    RepoService repoService;


    @GetMapping("/get-users")
    public ResponseEntity<Map<String,List<UserRecommend>>> recommendation(@RequestParam(value = "city", required = true) String city,
                                                                          @RequestParam(value = "age_start", required = true ) Integer age_start,
                                                                          @RequestParam(value = "age_end", required = true ) Integer age_end,
                                                                          @RequestParam(value = "genderPref", required = true) String genderPref,
                                                                          @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "20")int size,
                                                                          @RequestHeader("X-User-ID") String userId
                                             ) throws InterruptedException {


        size = Math.min(size, 20);

        List<UserRecommend> users = new ArrayList<>();

        try {
         users =    repoService.filterUserCityDB(city, page, size).stream()
                    .filter(user -> (age_start == null || user.getAge() >= age_start) &&
                            (age_end == null || user.getAge() < age_end) &&
                            (genderPref == null || user.getPreferenceGender().equals(genderPref)) &&
                            !user.getUuid().toString().equals(userId))
                    .collect(Collectors.toList());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("users",new ArrayList<>()));
        }


        return ResponseEntity.ok().body(Map.of("users", users));
    }
}

