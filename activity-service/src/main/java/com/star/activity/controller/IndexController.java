package com.star.activity.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.star.activity.openfeign.ResourceService;
import com.star.activity.service.ActivityService;
import com.star.common.entity.Activity;
import com.star.common.entity.ResourceFile;
import com.star.common.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    ActivityService activityService;

    @GetMapping("/index")
    public String index(@RequestParam("resourceId") Long resourceId){
        ResourceFile resource = resourceService.findResource(resourceId);
        log.info(resource.toString());
        log.info("index");
        return "ok";
    }

    @GetMapping("/detail")
    public Map index(@RequestParam("resourceId") Long resourceId,
                        @RequestParam("activityId") Long activityId){
        ResourceFile resource = resourceService.findResource(resourceId);
        Activity activity = activityService.getActivity(activityId);
        Map map = new HashMap();
        map.put("activity",activity);
        map.put("resource",activity);
        return map;
    }

    @PostMapping("/save")
    public String save(User user){
        Activity activity = new Activity();
        activity.setName("aaa");
        activity.setCreateTime(new Date());

        ResourceFile resourceFile = new ResourceFile();
        resourceFile.setName("bbbb");
        resourceFile.setModule("ccc");
        resourceFile.setObjectId("asdf");
        resourceFile.setCreateTime(new Date());

        boolean b = activityService.saveActivity(activity, resourceFile, user);
        log.info("保存活动和资源结果{}",b);
        return "ok";
    }

}
