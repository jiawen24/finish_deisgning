package com.graduate.backend.service.impl;

import com.graduate.backend.mapper.UserMapper;
import com.graduate.backend.pojo.User;
import com.graduate.backend.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//token业务接口实现
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }
}
