
package com.swipe.authservice.controller;
import com.swipe.authservice.model.User;
import com.swipe.authservice.model.UserCredential;
import com.swipe.authservice.service.JwtService;
import com.swipe.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequestMapping("/auth/")
@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class JwtController {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody User user){
         System.out.println(user.getPwd());
         String report = userService.createUser(user);
         return (report.equals("success")) ? ResponseEntity.ok("") : ResponseEntity.badRequest().body("Bad Request");
    }

    @PostMapping("/token")
    public String getToken(@RequestBody UserCredential userCredential) throws Exception{

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredential.getEmail(),userCredential.getPwd())
        );

        if(authentication.isAuthenticated()){

            UUID getId = userService.getUUID(userCredential.getEmail());

            return jwtService.generateToken(String.valueOf(getId), userCredential.getEmail());

        }
        throw new RuntimeException("user invalid access");
    }



}
