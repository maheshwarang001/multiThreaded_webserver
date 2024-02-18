package com.swipe.authservice.factory;

import com.swipe.authservice.dao.UserDAO;


public abstract class AbstractUserFactory {

    public UserDAO getDataSource(){
        return null;
    }

}
