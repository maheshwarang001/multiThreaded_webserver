package com.swipe.authservice.factory;

import com.swipe.authservice.dao.UserDAO;
import com.swipe.authservice.entity.UserMysqlDb;
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
