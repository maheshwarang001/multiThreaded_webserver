package com.swipe.authservice.service;
import java.security.Key;
import java.util.Map;

public abstract class JwtToken {

     public String generateToken(String user_id,String user_name){
         return null;
     };

     private String createToken(Map<String,Object> claims, String user_name){
         return null;
     }

     private Key getSignKey(){
         return null;
     }


}
