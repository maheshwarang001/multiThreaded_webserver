package com.swipeio.wesocket.redis.subscriber;

import com.swipeio.wesocket.dto.MessagePayLoad;
import com.swipeio.wesocket.websocket.WebSocketSessionManager;
import io.lettuce.core.pubsub.RedisPubSubListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;

@Slf4j
public class SubscribeHelper implements RedisPubSubListener<String, String> {

    private WebSocketSessionManager webSocketSessionManager;

    public SubscribeHelper(WebSocketSessionManager webSocketSessionManager){
        this.webSocketSessionManager = webSocketSessionManager;
    }

    @Override
    public void message(String channel, String messagePayLoad) {

        var ws = this.webSocketSessionManager.getWebSocketSessions(channel);

        try {
            ws.sendMessage(new TextMessage(messagePayLoad));
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public void message(String s, String k1, String messagePayLoad) {

    }

    @Override
    public void subscribed(String s, long l) {

    }

    @Override
    public void psubscribed(String s, long l) {

    }

    @Override
    public void unsubscribed(String s, long l) {

    }

    @Override
    public void punsubscribed(String s, long l) {

    }
}