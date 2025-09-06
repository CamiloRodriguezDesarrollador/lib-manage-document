package com.microcode.apigateway.services;

import com.microcode.apigateway.entity.Credentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AuthorizationServices {


    @Value("${authorization.api.url}")
    public String urlAuthorization;

    public WebClient webClient;

    public AuthorizationServices() {
        this.webClient = WebClient.builder()
                .build();
    }


    public Boolean validateAccessRoute(List<String> permissions, Credentials credentials) {
        MultiValueMap<String, List<String>> params = new LinkedMultiValueMap<>();
        params.add("permissions", permissions);

        try {
            return this.webClient.post()
                    .uri(urlAuthorization + "/validateAccessRoute")
                    .headers(headers -> {
                        if (credentials.getCurrentUser() != null)
                            headers.add("X-Current-User", credentials.getCurrentUser().toString());
                        if (credentials.getCurrentMail() != null)
                            headers.add("X-Current-Mail", credentials.getCurrentMail());
                        if (credentials.getCurrentClient() != null)
                            headers.add("X-Current-Client", credentials.getCurrentClient().toString());

                        if (credentials.getCurrentType() != null)
                            headers.add("X-Current-Type", credentials.getCurrentType().toString());
                    })
                    .bodyValue(params)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .onErrorResume(RestClientException.class, ex -> Mono.empty())
                    .block();
        } catch (Exception e) {
            return false;
        }
    }



}
