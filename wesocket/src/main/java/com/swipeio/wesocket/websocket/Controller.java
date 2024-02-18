package com.swipeio.wesocket.websocket;

import com.swipeio.wesocket.dto.MessagePayLoad;
import com.swipeio.wesocket.redis.publisher.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

//@org.springframework.stereotype.Controller
//public class Controller {
//
//    @Autowired
//    Publisher publisher;
//
//    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    public MessagePayLoad sendMessage(@Payload MessagePayLoad message) {
//       // publisher.publish(message);
//        return message;
//    }
//
//}
