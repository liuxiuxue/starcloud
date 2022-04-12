package com.star.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.star.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: liuxiuxue
 * @date: 2022/3/12 14:13
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
