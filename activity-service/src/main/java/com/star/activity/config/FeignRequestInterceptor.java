package com.star.activity.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Optional;

/**
 * @author: liuxiuxue
 * @date: 2022/4/11 11:17
 */
@Slf4j
@Service
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        log.info("Feign 调用, url: {} ", template.request().url());
        HttpServletRequest request = Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(it -> ((ServletRequestAttributes) it).getRequest()).orElse(null);
        if (request == null) {
            return;
        }
        Iterator<String> headerIterator = CollectionUtils.toIterator(request.getHeaderNames());
        while (headerIterator.hasNext()) {
            String name = headerIterator.next();
            log.info("add header, url: {} , path: {}", name, request.getHeader(name));
            // 只写入 Authorization
            if (HttpHeaders.AUTHORIZATION.equalsIgnoreCase(name)) {
                template.header(name, request.getHeader(name));
            }
        }
    }

}
