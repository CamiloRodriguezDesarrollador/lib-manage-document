//package com.microcode.apigateway;
//
//import io.github.resilience4j.ratelimiter.RateLimiter;
//import io.github.resilience4j.ratelimiter.RateLimiterConfig;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//import java.util.Map;
//import java.util.Objects;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Configuration
//public class IpRateLimiterFilter implements WebFilter {
//    private final Map<String, RateLimiter> rateLimiters = new ConcurrentHashMap<>();
//    private final Map<String, Long> blockedIps = new ConcurrentHashMap<>();
//    private static final long BLOCK_DURATION_MS = 24 * 60 * 60 * 1000; // 24 horas en milisegundos
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        String clientIp = Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress();
//
//        // Verificar si la IP está bloqueada
//        if (blockedIps.containsKey(clientIp)) {
//            long blockedUntil = blockedIps.get(clientIp);
//            if (System.currentTimeMillis() < blockedUntil) {
//                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
//                return exchange.getResponse().setComplete();
//            } else {
//                blockedIps.remove(clientIp);
//            }
//        }
//
//        // Obtener o crear RateLimiter por IP
//        RateLimiter rateLimiter = rateLimiters.computeIfAbsent(clientIp, ip ->
//                RateLimiter.of(ip, RateLimiterConfig.custom()
//                        .limitForPeriod(10)  // Máximo 10 requests
//                        .limitRefreshPeriod(Duration.ofSeconds(10))  // Cada 10 segundos
//                        .timeoutDuration(Duration.ZERO)
//                        .build()
//                )
//        );
//
//        if (rateLimiter.acquirePermission()) {
//            return chain.filter(exchange);
//        } else {
//            // Bloquear la IP por 30 segundos si supera el límite
//            blockedIps.put(clientIp, System.currentTimeMillis() + BLOCK_DURATION_MS);
//            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
//            return exchange.getResponse().setComplete();
//        }
//    }
//}
//
