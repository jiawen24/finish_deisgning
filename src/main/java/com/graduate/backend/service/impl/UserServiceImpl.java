package com.graduate.backend.service.impl;

import com.graduate.backend.mapper.UserMapper;
import com.graduate.backend.pojo.User;
import com.graduate.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//用户业务接口实现
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public User getUserById(int id) {
        return mapper.getUserById(id);
    }

}
