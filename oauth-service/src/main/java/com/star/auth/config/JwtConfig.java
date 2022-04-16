package com.star.auth.config;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import com.star.auth.entity.User;
import com.star.auth.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author: liuxiuxue
 * @date: 2022/3/10 11:15
 */
@Configuration
public class JwtConfig {

    private static final String GJJ = "gjj";

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserMapper userMapper;

    @Bean
    TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenEnhancer();
//        jwtAccessTokenConverter.setSigningKey(GJJ);
        PublicKey publicKey = SecureUtil.generatePublicKey("RSA", Base64.decode((String) (redisTemplate.opsForValue().get("publicKey"))));
        PrivateKey privateKey = SecureUtil.generatePrivateKey("RSA", Base64.decode((String) (redisTemplate.opsForValue().get("privateKey"))));
        KeyPair keyPair = new KeyPair(publicKey, privateKey);
        jwtAccessTokenConverter.setKeyPair(keyPair);
        return jwtAccessTokenConverter;
    }

    class JwtAccessTokenEnhancer extends JwtAccessTokenConverter {
        /**
         * 重写enhance方法，在其中扩展
         */
        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
            Object principal = authentication.getUserAuthentication().getPrincipal();
            if (principal instanceof User) {
                //获取userDetailService中查询到用户信息
                User user = (User) principal;
                //将额外的信息放入到LinkedHashMap中
                LinkedHashMap<String, Object> extendInformation = new LinkedHashMap<>();
                //设置用户的userId 并缓存redis中 1天有效时间 ,处理序列化问题
                Long userId = user.getId();
                com.star.common.entity.User userInfo = new com.star.common.entity.User();
                extendInformation.put("user_id", userId);
                if (userId != null) {
                    if (!redisTemplate.hasKey("user:" + userId)) {
                        User userDetail = userMapper.selectById(userId);
                        BeanUtils.copyProperties(userDetail, userInfo);
                        List<String> collect = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList());
                        userInfo.setAuthorities(collect);
                    }
                    redisTemplate.opsForValue().set("user:" + userId, userInfo, 1L, TimeUnit.DAYS);
                    redisTemplate.opsForValue().set("user:" + user.getUsername(), userInfo, 1L, TimeUnit.DAYS);
                }


                //添加到additionalInformation
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(extendInformation);


            }
            return super.enhance(accessToken, authentication);
        }

    }
}
