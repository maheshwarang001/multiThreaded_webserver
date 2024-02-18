package com.example.profileservice.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactoryProducer {


    @Autowired
    MysqlFactory mysqlDb;;

    public AbstractUserFactory getFactory(String src){

        switch (src.toLowerCase()){
            case "mysql" ->{
                return mysqlDb;
            }
            default -> {
                throw new NullPointerException();
            }


        }

    }
}
