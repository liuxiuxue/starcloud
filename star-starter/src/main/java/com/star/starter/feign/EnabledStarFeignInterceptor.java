package com.star.starter.feign;

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
@Import(OpenStarFeign.class)
public @interface EnabledStarFeignInterceptor {
}
