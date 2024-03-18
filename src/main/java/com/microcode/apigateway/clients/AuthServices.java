package com.microcode.apigateway.clients;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthServices {

    public String urlToken = "http://localhost:8080/api/auth/token";

    public WebClient webClient = null;

    public AuthServices() {
        this.webClient = WebClient.builder()
                .build();
    }

    public Mono<Boolean> validateToken(String currentToken) {
        return this.webClient.post()
                .uri(urlToken+"/validateToken")
                .header("Authorization", "Bearer " + currentToken)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorResume(RestClientException.class, ex -> Mono.empty());
    }

}
