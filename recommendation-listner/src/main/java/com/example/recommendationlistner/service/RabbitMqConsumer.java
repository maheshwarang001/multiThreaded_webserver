package com.example.recommendationlistner.service;


import com.example.recommendationlistner.config.RabbitMqConfig;
import com.example.recommendationlistner.model.RecommendationModel;
import com.example.recommendationlistner.model.UserCityDB;
import com.example.recommendationlistner.repository.RecommendationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RabbitMqConsumer {

    @Autowired
    RecommendationRepository repository;



    @RabbitListener(queues = RabbitMqConfig.UPDATE_USER_QUEUE)
    public void handleUpdateUserEvent(RecommendationModel recommendationModel) {
        System.out.println("Received update user event: " + recommendationModel.getUserId() + recommendationModel.getCity());

        try {
            UserCityDB val = convertIntoUserCity(recommendationModel);
            repository.save(val);
        } catch (Exception ex){
            log.error(ex.getMessage());
        }
    }


    private UserCityDB convertIntoUserCity(RecommendationModel recommendationModel){

        if(recommendationModel == null ||
                recommendationModel.getCity() == null ||
                recommendationModel.getCity().isEmpty() ||
                recommendationModel.getUserId() == null ||
                recommendationModel.getUserId().isEmpty() ||
                recommendationModel.getCountry() == null ||
                recommendationModel.getCountry().isEmpty()

        ){
            log.error("Something is missing");
            throw new IllegalArgumentException();
        }

        UserCityDB userCityDB = new UserCityDB();
        userCityDB.setUserId(UUID.fromString(recommendationModel.getUserId()));
        userCityDB.setCity(recommendationModel.getCity());
        userCityDB.setCountry(recommendationModel.getCountry());

        return userCityDB;
    }





}
