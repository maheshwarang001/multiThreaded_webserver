package com.example.recommendationservice.config;

import com.example.recommendationservice.model.MetaDataImage;
import io.netty.handler.proxy.HttpProxyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    private final WebClient webClient;

    @Autowired
    public ImageService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:9090/image").build();
    }

    public Mono<List<MetaDataImage>> imageServiceRequestWebClient(List<UUID> uuidList) {
        return webClient
                .post()
                .uri("/get-xmpp-list-meta-data")
                .bodyValue(uuidList)
                .retrieve()
                .bodyToFlux(MetaDataImage.class)
                .collectList();
    }

}
