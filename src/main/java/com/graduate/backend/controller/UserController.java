package com.graduate.backend.controller;

import com.graduate.backend.mapper.UserMapper;
import com.graduate.backend.pojo.Response;
import com.graduate.backend.pojo.User;
import com.graduate.backend.service.UserService;
import com.graduate.backend.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //登录
    @RequestMapping(value = "user/login",method = RequestMethod.POST)
    public String login(@RequestParam("username")String username,@RequestParam("password")String password)
    {
        return userService.login(username,password);
    }

    //注册
    @RequestMapping(value = "user/register",method = RequestMethod.POST)
    public Response register(@RequestParam("username")String username, @RequestParam("password")String password)
    {
        return userService.register(username,password);
    }

    //登出
    @RequestMapping(value = "user/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        return userService.logout(request);
    }

    @RequestMapping(value = "user/check",method = RequestMethod.GET)
    public Response checkToken(){
        return userService.checkToken();
    }

    //编辑信息
    @RequestMapping(value = "user/update",method = RequestMethod.POST)
    public Response update(HttpServletRequest request, @RequestParam(value = "username",required = false)String username,@RequestParam(value = "password",required = false)String password
         ,@RequestParam(value = "school",required = false)String school,@RequestParam(value = "major",required = false)String major){
        return userService.update(request, username, password, school, major);
    }

    //获取信息
    @RequestMapping(value = "user/getInfo",method = RequestMethod.GET)
    public User getUserInfo(HttpServletRequest request){
        return userService.getInfo(request);
    }

    //上传头像
    @RequestMapping(value = "user/uploadAvatar",method = RequestMethod.POST)
    public Response uploadAvatar(HttpServletRequest request,@RequestParam("avatar") MultipartFile file)
    {
        return userService.uploadAvatar(request, file);
    }

}
