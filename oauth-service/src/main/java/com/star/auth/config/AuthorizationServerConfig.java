package com.star.auth.config;

import com.star.auth.config.phone.PhonePasswordGranter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: liuxiuxue
 * @date: 2022/3/9 16:13
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisConnectionFactory connectionFactory;

    @Autowired
    DataSource dataSource;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenStore tokenStore;

    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //开启/oauth/token_key验证端口权限访问
        security.tokenKeyAccess("permitAll()")
                //开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("permitAll()")
                // 允许客户端id secret 通过表单提交
                .allowFormAuthenticationForClients();
        ;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 对客户端密码进行加密保存
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        AuthorizationServerTokenServices tokenServices = endpoints.getTokenServices();
        ClientDetailsService clientDetailsService = endpoints.getClientDetailsService();
        TokenGranter tokenGranter = endpoints.getTokenGranter();
        List<TokenGranter> tokenGranters = new ArrayList<>();
        tokenGranters.add(tokenGranter);
        tokenGranters.add(new PhonePasswordGranter(authenticationManager,tokenServices,clientDetailsService,
                new DefaultOAuth2RequestFactory(clientDetailsService)));

        //密码token方式必须配置
        endpoints.authenticationManager(authenticationManager)
                // 推荐RedisTokenStore 和 JWTTokenStore
                .tokenStore(tokenStore)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
                // token增强处理
                .tokenEnhancer(jwtAccessTokenConverter)
                // 授权码模式的code生成和消费
                .authorizationCodeServices(authorizationCodeServices())
                .tokenGranter(new CompositeTokenGranter(tokenGranters));

        ;
    }


//    @Bean
//    TokenStore tokenStore() {
//        // 是否要考虑序列化问题，好像也没必要
//        return new RedisTokenStore(connectionFactory);
//    }

    @Bean
    AuthorizationCodeServices authorizationCodeServices(){

        return new JdbcAuthorizationCodeServices(dataSource);
    }


}

