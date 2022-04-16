package com.star.auth;

import com.star.starter.redis.EnabledStarRedisTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: liuxiuxue
 * @date: 2022/4/8 13:58
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnabledStarRedisTemplate
public class AuthServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApp.class, args);
    }


}
