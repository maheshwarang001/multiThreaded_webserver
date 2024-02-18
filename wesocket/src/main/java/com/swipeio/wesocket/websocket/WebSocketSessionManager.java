package com.swipeio.wesocket.websocket;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class WebSocketSessionManager {


    private final Map<String, WebSocketSession> webSocketSessionMap = new HashMap<>();

    public void addWebSocketSession(WebSocketSession webSocketSession){
        String userId = WebSocketHelper.getUserIdFromSessionAttribute(webSocketSession);
        this.webSocketSessionMap.put(userId,webSocketSession);

        log.info("Added Session ID to user id",  webSocketSession.getId(), userId);
    }

    public void removeWebSocketSession(WebSocketSession webSocketSession){
        String userId = WebSocketHelper.getUserIdFromSessionAttribute(webSocketSession);
        this.webSocketSessionMap.remove(userId);
        log.info("Added Session ID to user id",  webSocketSession.getId(), userId);
    }

    public WebSocketSession getWebSocketSessions(String userID){
        return webSocketSessionMap.get(userID);
    }
}
