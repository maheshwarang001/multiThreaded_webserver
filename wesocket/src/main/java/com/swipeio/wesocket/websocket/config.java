package com.swipeio.wesocket.websocket;


import com.swipeio.wesocket.redis.publisher.Publisher;
import com.swipeio.wesocket.redis.subscriber.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Configuration
@EnableWebSocket
public class config implements WebSocketConfigurer {

    @Autowired
    private Publisher publisher;

    @Autowired
    private Subscriber subscriber;

    @Autowired
    private WebSocketSessionManager webSocketSessionManager;


    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        registry.addHandler(new WebSocketHandler(this.webSocketSessionManager,this.publisher,this.subscriber),"chat/user/*")
                .addInterceptors(getParametersInterceptors())
                .setAllowedOrigins("*");
    }

    @Bean
    public HandshakeInterceptor getParametersInterceptors() {
        return new HandshakeInterceptor() {
            @Override
            public boolean beforeHandshake(org.springframework.http.server.ServerHttpRequest request, org.springframework.http.server.ServerHttpResponse response, org.springframework.web.socket.WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
                String path = request.getURI().getPath();
                String userId = WebSocketHelper.getUserIdFromUrl(path);
                attributes.put(WebSocketHelper.userIdKey, userId);
                return true;
            }

            @Override
            public void afterHandshake(org.springframework.http.server.ServerHttpRequest request, org.springframework.http.server.ServerHttpResponse response, org.springframework.web.socket.WebSocketHandler wsHandler, Exception exception) {

            }
        };
    }




}
