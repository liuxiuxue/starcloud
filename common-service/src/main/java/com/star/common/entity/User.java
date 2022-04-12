package com.star.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * 对认证Spring Security User 的包装，用以在各个服务中获取User对象，同时处理redis对象的序列化问题
 * @author: liuxiuxue
 * @date: 2022/4/10 15:35
 */
@Data
@NoArgsConstructor
public class User implements Serializable {

    private Long id;

    private String username;

    private String phone;

    private String email;

    private Integer sex;

    private Date createTime;

    private Date updateTime;

    private Collection<String> authorities;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;
}
