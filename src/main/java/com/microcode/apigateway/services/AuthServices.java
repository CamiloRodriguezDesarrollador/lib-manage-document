package com.microcode.apigateway.services;

import com.microcode.apigateway.entity.Credentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthServices {

    @Value("${auth.api.url}")
    public String urlToken;

    public WebClient webClient;

    public AuthServices() {
        this.webClient = WebClient.builder()
                .build();
    }

    public Boolean validateToken(String token) {
        try{
            return this.webClient.post()
                    .uri(urlToken+"/validateToken")
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .onErrorResume(RestClientException.class, ex -> Mono.empty()).block();
        } catch (Exception e) {
            return false;
        }
    }

    public Credentials getCredentials(String token) {
        try{
            return this.webClient.get()
                    .uri(urlToken+"/credentials")
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Credentials.class)
                    .onErrorResume(RestClientException.class, ex -> Mono.empty()).block();
        } catch (Exception e) {
            return null;
        }
    }

    public String findMail(String token) {
        try{
            return this.webClient.post()
                    .uri(urlToken+"/findMail")
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .bodyToMono(String.class)
                    .onErrorResume(RestClientException.class, ex -> Mono.empty()).block();
        } catch (Exception e) {
            return null;
        }
    }
    public Integer findClient(String token) {
        try{
            return this.webClient.post()
                    .uri(urlToken+"/findClient")
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Integer.class)
                    .onErrorResume(RestClientException.class, ex -> Mono.empty()).block();
        } catch (Exception e) {
            return null;
        }
    }

    public Integer findType(String token) {
        try{
            return this.webClient.post()
                    .uri(urlToken+"/findType")
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Integer.class)
                    .onErrorResume(RestClientException.class, ex -> Mono.empty()).block();
        } catch (Exception e) {
            return null;
        }
    }

}
