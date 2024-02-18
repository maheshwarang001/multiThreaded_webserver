
package com.example.authenticationservice.controller;


import com.example.authenticationservice.model.User;
import com.example.authenticationservice.model.UserCredential;
import com.example.authenticationservice.service.JwtService;
import com.example.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;


@RequestMapping("/auth/")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class controller {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody User user) {


        System.out.println(user.getPwd());
        String report = userService.createUser(user);
        return (report.equals("success")) ? ResponseEntity.ok("") : ResponseEntity.badRequest().body("Bad Request");
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> getToken(@RequestBody UserCredential userCredential) throws Exception {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredential.getEmail(), userCredential.getPwd())
        );

        if (authentication.isAuthenticated()) {

            UUID getId = userService.getUUID(userCredential.getEmail());
            try {
                String token = jwtService.generateToken(String.valueOf(getId), userCredential.getEmail());
                return ResponseEntity.ok(Map.of("token", token));
            }
            catch (Exception e){
                return ResponseEntity.internalServerError().body(Map.of("token", "Failed ") );
            }

        }

        return ResponseEntity.badRequest().body(Map.of("token", "User not found "));

    }

    @GetMapping("/validate")
    public boolean validateToken(@RequestParam("token") String token) {
        return jwtService.validateToken(token);
    }


    @GetMapping("/test")
    public ResponseEntity<String> getMe(@RequestBody User user) {


        return ResponseEntity.ok().body(userService.getUUID(user.getEmail()).toString());

    }


}
