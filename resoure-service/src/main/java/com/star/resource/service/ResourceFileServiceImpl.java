package com.star.resource.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.resource.mapper.ResourceFileMapper;
import com.star.common.entity.ResourceFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: liuxiuxue
 * @date: 2022/4/8 10:03
 */
@Service
public class ResourceFileServiceImpl extends ServiceImpl<ResourceFileMapper, ResourceFile> implements ResourceFileService {

    @Autowired
    private ResourceFileMapper resourceFileMapper;

    //根据id查询ResourceFile
    @Override
    public ResourceFile findById(Long id) {
        return baseMapper.selectById(id);
    }

    // 保存ResourceFile
    @Override
    @Transactional
    public int saveResourceFile(ResourceFile resourceFile) {
        return baseMapper.insert(resourceFile);
    }
}
