package com.graduate.backend.controller;

import com.graduate.backend.mapper.UserMapper;
import com.graduate.backend.pojo.User;
import com.graduate.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable int id)
    {
        return userService.getUserById(id);
    }


}
