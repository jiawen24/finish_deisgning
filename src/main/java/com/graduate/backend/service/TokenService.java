package com.graduate.backend.service;

import com.graduate.backend.pojo.User;

//token接口类
public interface TokenService {

    public User getUserById(int id); //通过id获取用户
}
