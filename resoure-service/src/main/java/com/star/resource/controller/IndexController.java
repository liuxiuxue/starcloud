package com.star.resource.controller;

import com.star.common.entity.User;
import com.star.resource.service.ResourceFileService;
import com.star.common.entity.ResourceFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author: liuxiuxue
 * @date: 2022/4/7 17:23
 */
@Slf4j
@RefreshScope
@RestController
@RequestMapping("/resource")
public class IndexController {

    @Value("${custom.value}")
    private String custom;

    @Autowired
    ResourceFileService resourceFileService;

    @GetMapping("/custom")
    public String custom() {
        return custom;
    }

    // 测试自定义权限 只有超级管理员才能访问
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/index")
    public String index(User user) {
        return custom;
    }

    @GetMapping("/{id}")// 必须声明PathVariable name ，否则openfeign 无法解析
    public ResourceFile getById(@PathVariable("id") Long id) {
        return resourceFileService.findById(id);
    }

    @PostMapping("/save")
    public int save(@RequestBody ResourceFile resourceFile) {
        return resourceFileService.saveResourceFile(resourceFile);
    }

}

