package com.example.authenticationservice.factory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserFactoryProducer {

    @Autowired
    MysqlFactory mysqlFactory;

    public AbstractUserFactory get_factory(String name){

        /*
          EXCEPTION
          MORE DB, ADD SWITCH CASE
          **/


        if(!name.toLowerCase().equals("mysql")){
            throw new NullPointerException();
        }

        return mysqlFactory;

    }

}
