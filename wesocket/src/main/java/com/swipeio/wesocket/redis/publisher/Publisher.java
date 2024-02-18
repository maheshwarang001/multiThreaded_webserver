package com.swipeio.wesocket.redis.publisher;

import com.swipeio.wesocket.dto.MessagePayLoad;
import io.lettuce.core.RedisClient;
import org.springframework.stereotype.Component;


@Component
public class Publisher{

  RedisClient client;

  public Publisher(){
      this.client = RedisClient.create("redis://localhost:6379");
  }

  public void publish(String channel, String message){
      var connection  = this.client.connect();
      connection.sync().publish(channel,message);
  }

}
