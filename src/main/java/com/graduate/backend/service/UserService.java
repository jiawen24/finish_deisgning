package com.graduate.backend.service;

import com.graduate.backend.pojo.User;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

//用户接口类
public interface UserService {

    // 登录
    public String login(String name,String password);

    // 注册
    public String register(String name,String password);

    //登出
    public String logout(HttpServletRequest request);

    //验证token
    public String checkToken();

    // 修改用户信息
    public String update(HttpServletRequest request,String username,String password,String school,String major);


}
