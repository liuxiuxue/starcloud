package com.star.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author: liuxiuxue
 * @date: 2022/4/8 11:03
 */
@Data
public class ResourceFile {
    @TableId(type= IdType.ASSIGN_ID)
    private Long id;

    private String name;

    private String module;

    private String objectId;

    private Date createTime;

    private boolean enabled = true;
}
