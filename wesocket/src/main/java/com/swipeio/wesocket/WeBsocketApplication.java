package com.swipeio.wesocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//mport org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class WeBsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeBsocketApplication.class, args);
    }

}
