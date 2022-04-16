package com.star.starter.redis;

import com.star.starter.feign.OpenStarFeign;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: liuxiuxue
 * @date: 2022/4/15 16:34
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(OpenRedisTemplate.class)
public @interface EnabledStarRedisTemplate {
}
