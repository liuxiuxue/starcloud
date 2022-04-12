package com.star.resource.config;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author: liuxiuxue
 * @date: 2022/3/10 11:15
 */
@Configuration
public class JwtConfig {

    private static final String GJJ = "gjj";

    @Autowired
    RedisTemplate redisTemplate;

    @Bean
    TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//        jwtAccessTokenConverter.setSigningKey(GJJ);
        PublicKey publicKey = SecureUtil.generatePublicKey("RSA", Base64.decode((String) (redisTemplate.opsForValue().get("publicKey"))));
        PrivateKey privateKey = SecureUtil.generatePrivateKey("RSA",Base64.decode((String) (redisTemplate.opsForValue().get("privateKey"))));
        KeyPair keyPair = new KeyPair(publicKey, privateKey);
        jwtAccessTokenConverter.setKeyPair(keyPair);
        return jwtAccessTokenConverter;
    }
}
