package com.graduate.backend.controller;

import com.graduate.backend.mapper.UserMapper;
import com.graduate.backend.pojo.User;
import com.graduate.backend.service.UserService;
import com.graduate.backend.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "user/login",method = RequestMethod.POST)
    public String login(@RequestParam("username")String username,@RequestParam("password")String password)
    {
        return userService.login(username,password);
    }

    @RequestMapping(value = "user/register",method = RequestMethod.POST)
    public String register(@RequestParam("username")String username,@RequestParam("password")String password)
    {
        return userService.register(username,password);
    }

    @RequestMapping(value = "user/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        return userService.logout(request);
    }

    @RequestMapping(value = "user/check",method = RequestMethod.GET)
    public String checkToken(){
        return userService.checkToken();
    }

    @RequestMapping(value = "user/update",method = RequestMethod.POST)
    public String update(HttpServletRequest request, @RequestParam(value = "username",required = false)String username,@RequestParam(value = "password",required = false)String password
         ,@RequestParam(value = "school",required = false)String school,@RequestParam(value = "major",required = false)String major){
        return userService.update(request, username, password, school, major);
    }

}
