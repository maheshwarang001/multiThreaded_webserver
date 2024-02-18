package com.swipeio.wesocket.redis.subscriber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swipeio.wesocket.dto.MessagePayLoad;
import com.swipeio.wesocket.websocket.WebSocketSessionManager;
import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Slf4j
@Component
public class Subscriber {

    @Autowired
    private WebSocketSessionManager webSocketSessionManager;

    private RedisPubSubCommands<String, String> sync;

    public Subscriber(WebSocketSessionManager webSocketSessionManager){
        this.webSocketSessionManager = webSocketSessionManager;
        RedisClient client = RedisClient.create("redis://localhost:6379");


        StatefulRedisPubSubConnection<String, String> connection = client.connectPubSub();


        var redisListner = new SubscribeHelper(this.webSocketSessionManager);
        connection.addListener(redisListner);
        this.sync = connection.sync();
    }

    public void subscribe(String channel){
        this.sync.subscribe(channel);
    }

    public void  unsubscribe(String channel){
        this.sync.unsubscribe(channel);
    }




}
