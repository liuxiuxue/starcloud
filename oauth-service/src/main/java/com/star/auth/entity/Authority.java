package com.star.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: liuxiuxue
 * @date: 2022/3/12 13:56
 */
@Data
@TableName("authorities")
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long userId;

    private String role;
}
