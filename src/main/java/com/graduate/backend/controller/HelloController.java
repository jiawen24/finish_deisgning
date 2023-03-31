package com.graduate.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //测试接口
    @GetMapping(value = "/hello")
    public String testConnect()
    {
        return "hello world";
    }
}
