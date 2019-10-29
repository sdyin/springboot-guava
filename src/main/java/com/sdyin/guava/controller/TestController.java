package com.sdyin.guava.controller;

import com.sdyin.guava.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author liuye
 * @Date 2019/10/15 16:30
 **/
@RestController
public class TestController {

    @Autowired
    IUserService userService;

    @Autowired
    IUserService userOtherService;

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
