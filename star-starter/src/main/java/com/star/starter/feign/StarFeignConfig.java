package com.star.starter.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: liuxiuxue
 * @date: 2022/4/15 16:59
 */
@Slf4j
@Configuration
@ConditionalOnBean(OpenStarFeign.class)
public class StarFeignConfig {

    @Bean
    FeignRequestInterceptor feignRequestInterceptor(){
        log.info("star custom FeignRequestInterceptor init success");
        return new FeignRequestInterceptor();
    }
}
