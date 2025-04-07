package com.microcode.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration authCorsConfig = new CorsConfiguration();
        authCorsConfig.addAllowedOrigin("https://portal.genialw.com");
//        authCorsConfig.addAllowedOrigin("http://localhost:5173");
//        authCorsConfig.addAllowedOrigin("http://localhost:5174");
//        authCorsConfig.addAllowedOrigin("http://localhost:5175");
        authCorsConfig.addAllowedOrigin("http://localhost:4200");
//        authCorsConfig.addAllowedOrigin("http://localhost:8080");
        authCorsConfig.addAllowedMethod("*");
        authCorsConfig.addAllowedHeader("*");
        authCorsConfig.setMaxAge(3600L);
        authCorsConfig.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", authCorsConfig);

        return new CorsWebFilter(source);
    }

    @Bean
    public WebFilter optionsPreflightFilter() {
        return (ServerWebExchange exchange, WebFilterChain chain) -> {
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequest().getMethod().name())) {
                exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.OK);
                return exchange.getResponse().setComplete();
            }
            return chain.filter(exchange);
        };
    }
}
