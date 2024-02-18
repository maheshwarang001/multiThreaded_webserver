package com.example.recommendationservice.config;

import com.example.recommendationservice.model.MetaDataImage;
import com.example.recommendationservice.model.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
public class ProfileService {
    private final WebClient webClient;

    @Autowired
    public ProfileService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8085/profile").build();
    }

    public Mono<List<Metadata>> imageServiceRequestWebClient(List<UUID> uuidList) {
        return webClient
                .post()
                .uri("/serve-list-users")
                .bodyValue(uuidList)
                .retrieve()
                .bodyToFlux(Metadata.class)
                .collectList();
    }
}
