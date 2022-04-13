package com.star.activity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.star.common.entity.Activity;
import com.star.common.entity.ResourceFile;
import com.star.common.entity.User;

/**
 * @author: liuxiuxue
 * @date: 2022/4/12 15:07
 */
public interface ActivityService extends IService<Activity> {

    boolean saveActivity(Activity activity, ResourceFile resourceFile, User user);

    Activity getActivity(Long activityId);
}
