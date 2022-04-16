package com.star.activity.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.activity.mapper.ActivityMapper;
import com.star.activity.openfeign.ResourceService;
import com.star.common.entity.Activity;
import com.star.common.entity.ResourceFile;
import com.star.common.entity.User;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: liuxiuxue
 * @date: 2022/4/12 15:08
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Autowired
    ResourceService resourceService;

    //创建Activity 和ResourceFile
    @Override
    @GlobalTransactional
    public boolean saveActivity(Activity activity, ResourceFile resourceFile, User user) {
        resourceService.save(resourceFile);
//        int i = 1/0;
        activity.setUserId(user.getId());
        return baseMapper.insert(activity) > 0;
    }

    //查询Activity
    @Override
    public Activity getActivity(Long activityId) {
        return baseMapper.selectById(activityId);
    }
}
