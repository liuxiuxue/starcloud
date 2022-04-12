package com.star.activity.openfeign;

import com.star.common.entity.ResourceFile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: liuxiuxue
 * @date: 2022/4/11 10:33
 */
@Service
@FeignClient(name = "resource-service")
public interface ResourceService {

    @GetMapping("/resource/{id}")
     ResourceFile findResource(@PathVariable(name = "id") Long id);
}
