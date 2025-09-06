package com.microcode.apigateway.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.github.resilience4j.ratelimiter.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/redis")
@ResponseStatus(HttpStatus.OK)
@AllArgsConstructor
public class RedisController {

    private final StringRedisTemplate redisTemplate;
    private final ConcurrentHashMap<String, RateLimiter> rateLimiters = new ConcurrentHashMap<>();
    private static final long BLOCK_DURATION_HOURS = 24;

    @GetMapping("/is-block")
    public boolean isBlocked(@RequestParam(defaultValue = "") String clientIp) {
        String blockedKey = "blocked:" + clientIp;
        return Boolean.TRUE.equals(redisTemplate.hasKey(blockedKey));
    }

    @PostMapping("/add-block")
    public void addBlock(@RequestParam(defaultValue = "") String clientIp) {
        String blockedKey = "blocked:" + clientIp;
        redisTemplate.opsForValue().set(blockedKey, "true", BLOCK_DURATION_HOURS, TimeUnit.HOURS);
    }
}
