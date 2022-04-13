package com.star.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: liuxiuxue
 * @date: 2022/4/9 10:33
 */
@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter , Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求的url 和 http method
        ServerHttpRequest request = exchange.getRequest();
        String url = request.getURI().getPath();
        String method = request.getMethodValue();

        // 加载白名单信息，如果在白名单中，则放行 ,子服务模块也得相应放行这种白名单接口
        Map<String,String> whiteList = new HashMap<>();
        whiteList.put("/oauth/token","POST");
        if(whiteList.get(url) != null && whiteList.get(url).equals(method)){
            return chain.filter(exchange);
        }

        // 如果没有token，则提示无权限 返回401
        String authorization = request.getHeaders().getFirst("Authorization");
        if(StringUtils.isEmpty(authorization)){
            ServerWebExchangeUtils.setResponseStatus(exchange, HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
