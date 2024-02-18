package com.example.matchservice.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Component
public class Utils {


    public int consistentHashcode(UUID u1 , UUID u2){
        String impl = u1 + u2.toString();
        char [] arr = impl.toCharArray();
        Arrays.sort(arr);


        return new String(arr).hashCode();
    }
}
