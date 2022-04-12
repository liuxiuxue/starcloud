package com.star.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: liuxiuxue
 * @date: 2022/4/8 11:11
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GateWayServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(GateWayServiceApp.class, args);
    }
}
