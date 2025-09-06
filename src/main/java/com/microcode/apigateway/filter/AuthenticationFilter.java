package com.microcode.apigateway.filter;

import com.microcode.apigateway.entity.Credentials;
import com.microcode.apigateway.security.Path;
import com.microcode.apigateway.services.AuthServices;
import com.microcode.apigateway.services.AuthorizationServices;
import com.microcode.apigateway.services.NotifyServices;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final AuthServices authServices;
    private final AuthorizationServices authorizationServices;
    private final Path path;
    private final StringRedisTemplate redisTemplate;

    private final ConcurrentHashMap<String, RateLimiter> rateLimiters = new ConcurrentHashMap<>();
    private static final long BLOCK_DURATION_HOURS = 24;
    private final NotifyServices notifyServices;

    public AuthenticationFilter(AuthServices authServices,
                                AuthorizationServices authorizationServices,
                                Path path,
                                StringRedisTemplate redisTemplate, NotifyServices notifyServices) {
        super(Config.class);
        this.authServices = authServices;
        this.authorizationServices = authorizationServices;
        this.path = path;
        this.redisTemplate = redisTemplate;
        this.notifyServices = notifyServices;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
//            String clientIp = Objects.requireNonNull(exchange.getRequest().getRemoteAddress())
//                    .getAddress().getHostAddress();

            // 1️⃣ Verificar si esta bloqueado en REDIS
//            String blockedKey = "blocked:" + clientIp;
//            if (Boolean.TRUE.equals(redisTemplate.hasKey(blockedKey))) {
//                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
//                return exchange.getResponse().setComplete();
//            }
//
//            // 2️⃣ Limite de peticiónes por segundo
//            RateLimiter rateLimiter = rateLimiters.computeIfAbsent(clientIp, ip ->
//                    RateLimiter.of(ip, RateLimiterConfig.custom()
//                            .limitForPeriod(15)
//                            .limitRefreshPeriod(Duration.ofSeconds(1))
//                            .timeoutDuration(Duration.ZERO)
//                            .build()
//                    )
//            );
//
//            if (!rateLimiter.acquirePermission()) {
//                String msg = String.format("🚨 IP bloqueada: %s (exceso de peticiones)", clientIp);
//                notifyServices.notifyChatApps(msg);
//                // Bloquear IP en Redis por 24h
//                redisTemplate.opsForValue().set(blockedKey, "true", BLOCK_DURATION_HOURS, TimeUnit.HOURS);
//                exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
//                return exchange.getResponse().setComplete();
//            }

//            ServerHttpResponse response = exchange.getResponse();
//            response.setStatusCode(HttpStatus.OK);
//
//            return response.setComplete();

            // 3️⃣ Rutas abiertas
            String uri = exchange.getRequest().getURI().getPath();
            if (path.getOpenForUrl(uri)) {
                return chain.filter(exchange);
            }

            // 4️⃣ Autenticación y autorización
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }
            String token = authHeader.substring(7);

            if (!authServices.validateToken(token)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            Credentials credentials = authServices.getCredentials(token);
            ServerHttpRequest.Builder mutatedRequestBuilder = exchange.getRequest().mutate();

            if (path.getOpenForUrlToken(uri)) {
                if (credentials.getCurrentUser() != null)
                    mutatedRequestBuilder.header("X-Current-User", credentials.getCurrentUser().toString());
                if (credentials.getCurrentMail() != null)
                    mutatedRequestBuilder.header("X-Current-Mail", credentials.getCurrentMail());
                mutatedRequestBuilder.header("X-Current-Token", token);

                return chain.filter(exchange);
            }

            if (path.getOpenForAll(uri)) {
                if (credentials.getCurrentUser() != null)
                    mutatedRequestBuilder.header("X-Current-User", credentials.getCurrentUser().toString());
                if (credentials.getCurrentMail() != null)
                    mutatedRequestBuilder.header("X-Current-Mail", credentials.getCurrentMail());
                mutatedRequestBuilder.header("X-Current-Token", token);
                if (credentials.getCurrentClient() != null)
                    mutatedRequestBuilder.header("X-Current-Client", credentials.getCurrentClient().toString());
                if (credentials.getCurrentType() != null)
                    mutatedRequestBuilder.header("X-Current-Type", credentials.getCurrentType().toString());
                mutatedRequestBuilder.header("X-Current-Token", token);

                return chain.filter(exchange);
            }

            if (!authorizationServices.validateAccessRoute(token, path.getAuthorizedForUrl(uri))) {
                return onError(exchange, HttpStatus.FORBIDDEN);
            }

            if (credentials.getCurrentUser() != null)
                mutatedRequestBuilder.header("X-Current-User", credentials.getCurrentUser().toString());
            if (credentials.getCurrentMail() != null)
                mutatedRequestBuilder.header("X-Current-Mail", credentials.getCurrentMail());
            mutatedRequestBuilder.header("X-Current-Token", token);
            if (credentials.getCurrentClient() != null)
                mutatedRequestBuilder.header("X-Current-Client", credentials.getCurrentClient().toString());
            if (credentials.getCurrentType() != null)
                mutatedRequestBuilder.header("X-Current-Type", credentials.getCurrentType().toString());
            mutatedRequestBuilder.header("X-Current-Token", token);

            ServerHttpRequest mutatedRequest = mutatedRequestBuilder.build();
            return chain.filter(exchange.mutate().request(mutatedRequest).build());
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus status) {
        exchange.getResponse().setStatusCode(status);
        return exchange.getResponse().setComplete();
    }

    public static class Config {}

    private void addBaseHeaders(ServerHttpRequest.Builder builder, Credentials credentials, String token) {
        if (credentials.getCurrentUser() != null) {
            builder.header("X-Current-User", credentials.getCurrentUser().toString());
        }
        if (credentials.getCurrentMail() != null) {
            builder.header("X-Current-Mail", credentials.getCurrentMail());
        }
        builder.header("X-Current-Token", token);
    }

    private void addExtendedHeaders(ServerHttpRequest.Builder builder, Credentials credentials) {
        if (credentials.getCurrentClient() != null) {
            builder.header("X-Current-Client", credentials.getCurrentClient().toString());
        }
        if (credentials.getCurrentType() != null) {
            builder.header("X-Current-Type", credentials.getCurrentType().toString());
        }
    }

}

