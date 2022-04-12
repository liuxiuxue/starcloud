package com.star.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.star.auth.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author: liuxiuxue
 * @date: 2022/3/12 14:13
 */
public interface UserService extends IService<User>, UserDetailsService {

}
