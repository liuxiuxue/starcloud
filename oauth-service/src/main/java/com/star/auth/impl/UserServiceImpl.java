package com.star.auth.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.auth.entity.Authority;
import com.star.auth.entity.User;
import com.star.auth.mapper.AuthorityMapper;
import com.star.auth.mapper.UserMapper;
import com.star.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: liuxiuxue
 * @date: 2022/3/12 14:14
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AuthorityMapper authorityMapper;


    @Override
    @Transactional
    public boolean save(User entity) {
        String username = entity.getUsername();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        User exist = userMapper.selectOne(wrapper);
        if (exist != null){
            log.info("{},{}已存在",username,entity.getPhone());
            return false;
        }
        Authority authority = new Authority();
        User user = new User(entity);
        user.setPassword(passwordEncoder.encode(entity.getPassword()));
        int insert = userMapper.insert(user);
        authority.setRole("ROLE_USER");
        authority.setUserId(user.getId());
        authorityMapper.insert(authority);
        return insert > 0;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 查用户  2.设置权限
        //TODO 如果是手机号和邮箱登录判定，在这里也是可以的
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            log.info("{} is  not exist!", username);
            QueryWrapper wrapper1 = new QueryWrapper();
            wrapper1.eq("phone", username);
            user = userMapper.selectOne(wrapper1);
            if (user == null) {
                log.info("{} is  not exist!", username);
            }
        }

        Long id = user.getId();
        LambdaQueryWrapper<Authority> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Authority::getUserId, id);
        List<Authority> authorities = authorityMapper.selectList(lambdaQueryWrapper);
        List<String> collect = authorities.stream().map(Authority::getRole).collect(Collectors.toList());
        user.setAuthorities(AuthorityUtils.createAuthorityList(Convert.toStrArray(collect)));
        return user;
    }
}
