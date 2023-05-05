package com.graduate.backend.service;

import com.graduate.backend.pojo.Response;
import com.graduate.backend.pojo.User;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//用户接口类
public interface UserService {

    // 登录
    public String login(String name,String password);

    // 注册
    public Response register(String name,String password);

    //登出
    public String logout(HttpServletRequest request);

    //验证token
    public Response checkToken();

    //获取用户信息
    public User getInfo(HttpServletRequest request);

    // 修改用户信息
    public Response update(HttpServletRequest request,String username,String password,String school,String major);

    //上传头像
    public Response uploadAvatar(HttpServletRequest request, MultipartFile file);
}
