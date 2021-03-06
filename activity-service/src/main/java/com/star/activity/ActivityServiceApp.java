package com.star.activity;

import com.star.starter.feign.EnabledStarFeignInterceptor;
import com.star.starter.redis.EnabledStarRedisTemplate;
import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author: liuxiuxue
 * @date: 2022/4/11 10:25
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnabledStarRedisTemplate
@EnabledStarFeignInterceptor
public class ActivityServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ActivityServiceApp.class, args);
    }
    @Bean
    Logger.Level feignLogger(){
        return Logger.Level.HEADERS;
    }

}
