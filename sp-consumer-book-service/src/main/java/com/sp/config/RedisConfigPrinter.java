package com.sp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RedisConfigPrinter {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @PostConstruct
    public void printRedisConfig() {
        System.err.println("Redis Host: " + redisHost);
        System.err.println("Redis Port: " + redisPort);
        System.err.println("Redis Password: " + redisPassword);
    }
}
