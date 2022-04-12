package com.star.resource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.star.common.entity.ResourceFile;

/**
 * @author: liuxiuxue
 * @date: 2022/4/8 9:59
 */
public interface ResourceFileService extends IService<ResourceFile> {

    ResourceFile findById(Long id);

    int saveResourceFile(ResourceFile resourceFile);
}
