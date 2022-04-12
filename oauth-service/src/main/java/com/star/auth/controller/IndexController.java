package com.star.auth.controller;

import com.star.auth.entity.User;
import com.star.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: liuxiuxue
 * @date: 2022/3/11 10:34
 */
@RestController
@RequestMapping("/oauth")
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @PostMapping("/user/register")
    public boolean save(@RequestBody User user){

        return userService.save(user);
    }
}
