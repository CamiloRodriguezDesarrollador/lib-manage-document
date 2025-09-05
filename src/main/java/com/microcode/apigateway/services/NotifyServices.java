package com.microcode.apigateway.services;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class NotifyServices {

    @Value("${notify.api.url}")
    public String urlNotify;

    public WebClient webClient;

    public NotifyServices() throws SSLException {
        SslContext sslContext = SslContextBuilder.forClient()
                .protocols("TLSv1.2")
                .build();

        HttpClient httpClient = HttpClient.create()
                .secure(t -> t.sslContext(sslContext));

        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(HttpHeaders.ACCEPT, "*/*")
                .defaultHeader(HttpHeaders.USER_AGENT, "Scheduled-Task")
                .build();
    }

    public void notifyChatApps(String text) {
        Map<String, String> body = new HashMap<>();
        body.put("spaceId", "5BoLpCAAAAE");
        body.put("text", text);
        try {
            this.webClient.post()
                    .uri(urlNotify + "/send-notify-chat")
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .onErrorResume(ex -> Mono.empty())
                    .subscribe();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




}
