package com.example.profileservice.service;

import com.example.profileservice.dao.UserProfileDao;
import com.example.profileservice.entity.ProfileMysqlDb;
import com.example.profileservice.factory.FactoryProducer;
import com.example.profileservice.factory.MysqlFactory;
import com.example.profileservice.model.Metadata;
import com.example.profileservice.model.UserDetail;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class UserService implements UserServiceImpl{

    @Autowired
    FactoryProducer factoryProducer;


    @Override
    public void createUserProfile(UserDetail userDetail) {


        if(userDetail.getUserId() == null){
            throw new NullPointerException();
        }

        UserProfileDao userProfileDao = factoryProducer.getFactory("mysql").getDataSource();


        if(userProfileDao.getUserProfile(userDetail.getUserId()) != null){
            throw new EntityExistsException();
        }

        userProfileDao.createUserProfile(userDetail);
    }

    @Override
    public void updateProfile(UserDetail userDetail) {
        UserProfileDao userProfileDao = factoryProducer.getFactory("mysql").getDataSource();

        userProfileDao.updateUser(userDetail);

    }

    @Override
    public void deleteProfile(UUID userId) {

    }

    @Override
    public UserDetail getUserProfile(UUID userId) {
        UserProfileDao userProfileDao = factoryProducer.getFactory("mysql").getDataSource();

        return userProfileDao.getUserProfile(userId);
    }

    @Override
    public List<Metadata> getMetaData(List<UUID> uuidList) {
        UserProfileDao userProfileDao = factoryProducer.getFactory("mysql").getDataSource();

        List<UserDetail> userDetails = userProfileDao.serveUserMetadata(uuidList);

        if(userDetails == null) return null;

        List<Metadata> addData = new ArrayList<>();

        for(UserDetail it: userDetails){

            Metadata metadata = new Metadata();
            metadata.setUuid(it.getUserId());
            metadata.setAge(it.getBiological().getAge());
            metadata.setFirstname(it.getFirstName());
            metadata.setLastname(it.getLastName());
            metadata.setUsersex(it.getBiological().getSex());
            metadata.setUserpreference(it.getPreference().getPreference());
            metadata.setCity(it.getGeoLocation().getCity());
            metadata.setCountry(it.getGeoLocation().getCountry());

            addData.add(metadata);

        }

        return addData;
    }
}
