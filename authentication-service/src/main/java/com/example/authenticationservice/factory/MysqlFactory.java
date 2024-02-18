package com.example.authenticationservice.factory;


import com.example.authenticationservice.DAO.UserDAO;
import com.example.authenticationservice.entity.UserMysqlDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MysqlFactory extends AbstractUserFactory {

    @Autowired
    UserMysqlDb userMysqlDb;

    public UserDAO getDataSource(){
        return userMysqlDb;
    }

}
