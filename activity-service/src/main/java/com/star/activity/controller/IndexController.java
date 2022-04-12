package com.star.activity.controller;

import com.star.activity.openfeign.ResourceService;
import com.star.common.entity.ResourceFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: liuxiuxue
 * @date: 2022/4/11 10:32
 */
@Slf4j
@RefreshScope
@RestController
@RequestMapping("/activity")
public class IndexController {

    @Autowired
    ResourceService resourceService;

    @RequestMapping("/index")
    public String index(@RequestParam("resourceId") Long resourceId){
        ResourceFile resource = resourceService.findResource(resourceId);
        log.info(resource.toString());
        log.info("index");
        return "ok";
    }
}
