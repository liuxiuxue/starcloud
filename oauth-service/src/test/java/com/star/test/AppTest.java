package com.star.test;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.RSA;
import com.star.auth.AuthServiceApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author: liuxiuxue
 * @date: 2022/4/10 16:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServiceApp.class)
public class AppTest {

    @Autowired
    RedisTemplate redisTemplate;


    @Test
    public void test1(){
        RSA rsa = SecureUtil.rsa();
        PublicKey publicKey = rsa.getPublicKey();
        PrivateKey privateKey = rsa.getPrivateKey();
        redisTemplate.opsForValue().set("publicKey",publicKey.getEncoded());
        redisTemplate.opsForValue().set("privateKey",privateKey.getEncoded());
    }
}
