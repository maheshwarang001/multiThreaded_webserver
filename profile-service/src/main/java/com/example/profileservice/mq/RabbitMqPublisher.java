package com.example.profileservice.mq;

import com.example.profileservice.config.RabbitMqConfig;
import com.example.profileservice.model.RecommendationModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class RabbitMqPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void publishUpdateUserEvent(RecommendationModel recommendationModel){

        Thread thread = new Thread(() ->
                rabbitTemplate.convertAndSend(
                RabbitMqConfig.EXCHANGE,
                RabbitMqConfig.USER_ROUTE,
                recommendationModel,
                        message -> {
                            message.getMessageProperties().setDelay(500000);
                            return message;
                        }
        ));
        thread.start();
    }


}
