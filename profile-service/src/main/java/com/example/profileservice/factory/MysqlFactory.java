package com.example.profileservice.factory;

import com.example.profileservice.dao.UserProfileDao;
import com.example.profileservice.entity.ProfileMysqlDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MysqlFactory extends AbstractUserFactory{

   @Autowired
   ProfileMysqlDb profileMysqlDb;

    public UserProfileDao getDataSource(){

        return profileMysqlDb;
    }

}
