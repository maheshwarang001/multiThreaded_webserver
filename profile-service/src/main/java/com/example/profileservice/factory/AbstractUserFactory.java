package com.example.profileservice.factory;

import com.example.profileservice.dao.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractUserFactory {

    public UserProfileDao getDataSource(){
        return null;
    }
}
