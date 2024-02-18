package com.example.imageservice.controller;


import com.example.imageservice.model.MetaDataImage;
import com.example.imageservice.service.StorageService;
import org.hibernate.Internal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/image")
public class Controller {

    @Autowired
    private StorageService service;

    @PostMapping("/upload")
    public ResponseEntity<Map<String,String>> uploadFile(@RequestParam(value = "file") MultipartFile file,
                                                         @RequestHeader("X-User-ID") String userId) throws IOException {

        if(file == null){
            ResponseEntity.internalServerError().body("Invalid");
        }
        if(userId == null || userId.isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("response","ID NOT FOUND"));
        }

        try {

            service.checker(file,userId);

            Thread.sleep(2000);

        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("response"," NOT FOUND"));
        }

        return  ResponseEntity.ok().body(Map.of("response","Successful"));
    }


    @GetMapping("/get-meta-data")
    public MetaDataImage getData(   @RequestHeader("X-User-ID") String userId){
        return service.servicegetmeta(UUID.fromString(userId));
    }

    @GetMapping("/get-user-exist")
    public ResponseEntity<Map<String,String>> checkUserExist( @RequestHeader("X-User-ID") String userId){
        boolean response =  service.checkExistByUuid(UUID.fromString(userId));
        return ResponseEntity.ok(Map.of("response",response + ""));
    }


    @PostMapping("/get-xmpp-list-meta-data")
    public List<MetaDataImage> getmetaDataUsers(@RequestBody List<UUID> uuids){
        return service.getAllMetaData(uuids);
    }
}
