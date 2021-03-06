package com.star.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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

//    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private boolean enabled = true;
}
