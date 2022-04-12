package com.star.resource;

import feign.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author: liuxiuxue
 * @date: 2022/4/7 14:01
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class ResourceServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ResourceServiceApp.class, args);
    }

    @Bean
    Logger.Level feignLogger(){
        return Logger.Level.HEADERS;
    }
}
