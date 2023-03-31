package com.graduate.backend.service;

import com.graduate.backend.pojo.User;
import org.springframework.stereotype.Service;

//用户接口类
public interface UserService {

    public User getUserById(int id); //通过id获取用户
}
