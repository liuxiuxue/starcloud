package com.star.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author: liuxiuxue
 * @date: 2022/4/12 14:53
 */

@Data
public class Activity {
    @TableId(type= IdType.ASSIGN_ID)
    private Long id;

    private String name;

    private Long userId;

    private Date createTime;

    private boolean enabled = true;

}
