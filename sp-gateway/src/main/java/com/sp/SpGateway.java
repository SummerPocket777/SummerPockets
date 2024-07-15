package com.sp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class SpGateway {

    @Value("${spring.redis.database}")
    private int redisDatabase;

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${spring.redis.timeout}")
    private String redisTimeout;

    @Value("${spring.redis.lettuce.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.lettuce.pool.max-wait}")
    private String maxWait;

    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.lettuce.pool.min-idle}")
    private int minIdle;

    public static void main(String[] args) {
        SpringApplication.run(SpGateway.class, args);
    }

    @Bean
    public CommandLineRunner printRedisConfig() {
        return args -> {
            log.error("Redis Configuration:");
            System.out.println("Database: " + redisDatabase);
            log.error("Database: " + redisDatabase);
            System.out.println("Host: " + redisHost);
            log.error("Host: " + redisHost);
            System.out.println("Port: " + redisPort);
            log.error("Port: " + redisPort);
            System.out.println("Password: " + redisPassword);
            log.error("Password: " + redisPassword);
            System.out.println("Timeout: " + redisTimeout);
            log.error("Timeout: " + redisTimeout);
            System.out.println("Lettuce Pool Configurations:");
            log.error("Lettuce Pool Configurations:");
            System.out.println("  Max Active: " + maxActive);
            log.error("  Max Active: " + maxActive);
            System.out.println("  Max Wait: " + maxWait);
            log.error("  Max Wait: " + maxWait);
            System.out.println("  Max Idle: " + maxIdle);
            log.error("  Max Idle: " + maxIdle);
            System.out.println("  Min Idle: " + minIdle);
            log.error("  Min Idle: " + minIdle);
        };
    }
}
