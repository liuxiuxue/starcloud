package com.star.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.star.auth.entity.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: liuxiuxue
 * @date: 2022/3/12 15:24
 */
@Mapper
public interface AuthorityMapper extends BaseMapper<Authority> {
}
