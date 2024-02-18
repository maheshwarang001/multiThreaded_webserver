package com.example.profileservice.controller;


import brave.Response;
import com.example.profileservice.model.Metadata;
import com.example.profileservice.model.UserDetail;
import com.example.profileservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/profile/")
public class ProfileController {

    @Autowired
    UserService userService;

    @PostMapping("/create-profile")
    public ResponseEntity<Map<String,String>> createUser(@RequestBody UserDetail userDetail,
                                                         @RequestHeader("X-User-ID") String userId){

        if(userId == null || userId.isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("response","Bad Request"));
        }

        try {

            userDetail.setUserId(UUID.fromString(userId));

            userService.createUserProfile(userDetail);

            return ResponseEntity.badRequest().body(Map.of("response","Successful"));

        }
        catch (Exception e){

            log.error(e.getMessage());

            return ResponseEntity.badRequest().body(Map.of("response","Something Went Wrong"));

        }
    }

    @PostMapping("/update-profile")
    public ResponseEntity<String> update(@RequestBody UserDetail userDetail,
                                             @RequestHeader("X-User-ID") String userId){
        if(userId == null || userId.isEmpty()){
            return ResponseEntity.badRequest().body("userId not found");
        }



        try {

            userDetail.setUserId(UUID.fromString(userId));

            userService.updateProfile(userDetail);

            return ResponseEntity.ok().body("Successful");

        }
        catch (Exception e){

            log.error(e.getMessage());

            return ResponseEntity.badRequest().body("Something Went wrong");

        }
    }

    @GetMapping("/get-profile")
    public UserDetail getProfile( @RequestHeader("X-User-ID") String userId){
        try {

           return userService.getUserProfile(UUID.fromString(userId));

        }

        catch (Exception e){

            log.error(e.getMessage());

            return null;

        }
    }


    @GetMapping("/get-user-profile")

    public ResponseEntity<Map<String,String>> checkUser(@RequestHeader("X-User-ID") String userId){

        boolean response = userService.getUserProfile(UUID.fromString(userId)) != null;

        return ResponseEntity.ok(Map.of("response",response+""));

    }

    @PostMapping("/serve-list-users")
    public List<Metadata> serveMetadata(@RequestBody List<UUID> userList){

        if(userList == null || userList.size() == 0) return null;

        return userService.getMetaData(userList);
    }

}
