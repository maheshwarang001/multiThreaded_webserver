package com.swipeio.wesocket.websocket;

import com.swipeio.wesocket.redis.publisher.Publisher;
import com.swipeio.wesocket.redis.subscriber.Subscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {


    private final Publisher publisher;


    private final Subscriber subscriber;


    private final WebSocketSessionManager webSocketSessionManager;


    public WebSocketHandler( WebSocketSessionManager webSocketSessionManager,Publisher publisher, Subscriber subscriber) {
        this.publisher = publisher;
        this.subscriber = subscriber;
        this.webSocketSessionManager = webSocketSessionManager;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession socketSession) throws Exception{
        webSocketSessionManager.addWebSocketSession(socketSession);
        String userID = WebSocketHelper.getUserIdFromSessionAttribute(socketSession);
        this.subscriber.subscribe(userID);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        this.webSocketSessionManager.removeWebSocketSession(session);
        String userId = WebSocketHelper.getUserIdFromSessionAttribute(session);
        this.subscriber.unsubscribe(userId);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws IOException {
        String payload = message.getPayload();
        String[] payLoadSplit = payload.split("->");
        String targetUserId  = payLoadSplit[0].trim();
        String messageToBeSent = payLoadSplit[1].trim();
        String userId = WebSocketHelper.getUserIdFromSessionAttribute(session);
        log.info("got the payload {} and going to send to channel {}", payload, targetUserId);
        this.publisher.publish(targetUserId, userId + ":" + messageToBeSent);
    }







}
