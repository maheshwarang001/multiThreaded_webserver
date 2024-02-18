package com.example.profileservice.entity;

import com.example.profileservice.dao.UserProfileDao;
import com.example.profileservice.model.*;
import com.example.profileservice.mq.RabbitMqPublisher;
import com.example.profileservice.repository.LocationRepository;
import com.example.profileservice.repository.PreferenceRepository;
import com.example.profileservice.repository.UserBiologicalRepository;
import com.example.profileservice.repository.UserDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class ProfileMysqlDb implements UserProfileDao {

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    UserBiologicalRepository userBiologicalRepository;

    @Autowired
    PreferenceRepository preferenceRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    RabbitMqPublisher rabbitMqPublisher;


    @Override
    public void createUserProfile(UserDetail userDetail) {
        try {

            if(userDetail.getBiological() == null ||
            userDetail.getPreference() == null ||
            userDetail.getGeoLocation() == null ||
                    userDetail.getUserId() == null ||
                    userDetail.getFirstName() == null ||
                    userDetail.getLastName() == null
            ){
                throw new NullPointerException();
            }




            userBiologicalRepository.save(userDetail.getBiological());
            preferenceRepository.save(userDetail.getPreference());
            locationRepository.save(userDetail.getGeoLocation());

            userDetailRepository.save(userDetail);


            createUserPublisher(userDetail);

        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException();
        }
    }

    private void createUserPublisher(UserDetail userDetail){

        RecommendationModel recommendationModel = new RecommendationModel();
        recommendationModel.setUserId(userDetail.getUserId().toString());
        recommendationModel.setCity(userDetail.getGeoLocation().getCity());
        recommendationModel.setCountry(userDetail.getGeoLocation().getCountry());

        rabbitMqPublisher.publishUpdateUserEvent(recommendationModel);



    }

    private void updateUserPublisher(UUID userID,GeoLocation geoLocation){

        RecommendationModel recommendationModel = new RecommendationModel();
        recommendationModel.setUserId(userID.toString());
        recommendationModel.setCity(geoLocation.getCity());
        recommendationModel.setCountry(geoLocation.getCountry());

        rabbitMqPublisher.publishUpdateUserEvent(recommendationModel);



    }

    @Override
    public void updateUser(UserDetail userDetail){

        UserDetail user = userDetailRepository.findByUserId(userDetail.getUserId());


        if(user != null){


            UUID user_ID = user.getUserId();
            UUID location_ID = user.getGeoLocation().getGeolocationId() ;
            UUID pref_id = user.getPreference().getPrefId();
            UUID biological_id = user.getBiological().getUserBioId();



            if(userDetail.getBiological() != null){
                updateUserBiological(biological_id,userDetail.getBiological());
            }
            if(userDetail.getPreference() != null){
                updatePreferences(pref_id,userDetail.getPreference());
            }
            if(userDetail.getGeoLocation() != null){

               updateGeoLocation(user_ID,location_ID,userDetail.getGeoLocation());

            }

            if(userDetail.getFirstName() != null || userDetail.getLastName() != null){
                updateUserDetail(user_ID,userDetail);
            }







        }else{
            throw new NullPointerException();
        }


    }




    private void updateUserDetail(UUID id, UserDetail userDetail) {
        UserDetail user = userDetailRepository.findByUserId(id);

        if(user != null){

            if(userDetail.getFirstName() != null){
                user.setFirstName(userDetail.getFirstName());
            }
            if(userDetail.getLastName() != null){
                user.setLastName(userDetail.getLastName());
            }

            userDetailRepository.save(user);
        }
    }


    private void updateUserBiological(UUID id, UserBiological userBiological) {

        UserBiological user = userBiologicalRepository.findByUserBioId(id);

        if(user != null){

            if(userBiological.getAge() != null){
                user.setAge(userBiological.getAge());
            }
            if(userBiological.getSex() != null){
                user.setSex(userBiological.getSex());
            }

            userBiologicalRepository.save(user);
        }


    }



    private void updatePreferences(UUID id, Preferences preferences) {
        Preferences pref = preferenceRepository.findByPrefId(id);

        if(pref != null){

            if(preferences.getPreference() != null){
                pref.setPreference(preferences.getPreference());
            }

            preferenceRepository.save(pref);
        }

    }


    private void updateGeoLocation(UUID userId, UUID locationId, GeoLocation location) {

        GeoLocation geo = locationRepository.findByGeolocationId(locationId);

        if(geo != null){

            if(location.getCity() != null){
                geo.setCity(location.getCity());
            }
            if(location.getCountry() != null){
                geo.setCountry(location.getCountry());
            }

            locationRepository.save(geo);


            updateUserPublisher(userId,geo);

        }

    }


    @Override
    public void deleteProfile(UUID userId) {
        userDetailRepository.deleteById(userId);
    }

    @Override
    public UserDetail getUserProfile(UUID userId) {
        if(userDetailRepository.existsById(userId)){
            return userDetailRepository.findByUserId(userId);
        }

       return null;
    }

    @Override
    public List<UserDetail> serveUserMetadata(List<UUID> uuids) {
        return userDetailRepository.findByUuids(uuids);
    }


}
