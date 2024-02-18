package com.example.authenticationservice.factory;


import com.example.authenticationservice.DAO.UserDAO;

public abstract class AbstractUserFactory {

    public UserDAO getDataSource(){
        return null;
    }

}
