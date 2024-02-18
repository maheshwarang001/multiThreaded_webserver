package com.swipeio.wesocket.websocket;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;


public class WebSocketHelper {

    public static String userIdKey = "userID";

    public static String getUserIdFromSessionAttribute(WebSocketSession webSocketSession){
        return (String) webSocketSession.getAttributes().get(userIdKey);
    }

    public static String getUserIdFromUrl(String url){
        return url.substring(url.lastIndexOf('/')+1);
    }

}
