//package com.microcode.apigateway;
//
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpHeaders;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Configuration
//public class ContentSecurityPolicyFilter implements GatewayFilter, Ordered {
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        HttpHeaders headers = exchange.getResponse().getHeaders();
//
//        String cspPolicy = "script-src 'self'; style-src 'self'";
//
//        headers.add("Content-Security-Policy", cspPolicy);
//
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return Ordered.HIGHEST_PRECEDENCE;
//    }
//}
