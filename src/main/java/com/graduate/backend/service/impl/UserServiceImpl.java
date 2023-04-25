package com.graduate.backend.service.impl;

import com.graduate.backend.mapper.UserMapper;
import com.graduate.backend.pojo.User;
import com.graduate.backend.service.UserService;
import com.graduate.backend.util.TokenUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//用户业务接口实现
@Service
public class UserServiceImpl implements UserService {

    private final String STATUS_FAILED = "failed";
    private final String STATUS_SUCCESS = "success";
    private final String STATUS = "status";
    private final String MSG = "msg";
    private final String TOKEN = "token";
    private final String USER = "user";

    @Autowired
    private UserMapper mapper;

    @Autowired
    private TokenUtil tokenUtil;

    /*
     * 登录
     * return { "status":"success/failed","msg":"账户名密码错误","token":""}
     * */
    @Override
    public String login(String name, String password) {
        Map<String,String> res = new HashMap<>();
        User user = mapper.getUserByNameAndPass(name,password);
        if(user !=null){
            String token = tokenUtil.createToken(user.getId(),user.getUsername());
            res.put(STATUS,STATUS_SUCCESS);
            res.put(TOKEN,token);
            res.put(USER,new JSONObject(user).toString());
        }else{
            res.put(STATUS, STATUS_FAILED);
            res.put(MSG,"账户名密码错误");
        }
        return new JSONObject(res).toString();
    }

    /*
     * 注册
     * return { "status":"success/failed","msg":"success/unknown/账户名已存在"}
     * */
    @Override
    public String register(String name, String password) {
        Map<String,String> res = new HashMap<>();
        User user = mapper.getUserByName(name);
        if(user==null){
            int insertRes = mapper.insert(name, password);
            if(insertRes>0){ //插入成功
                res.put(STATUS,STATUS_SUCCESS);
                res.put(MSG,STATUS_SUCCESS);
            }else{ //插入失败
                res.put(STATUS,STATUS_FAILED);
                res.put(MSG,"unknown");
            }
        }else{ //用户名已存在
            res.put(STATUS,STATUS_FAILED);
            res.put(MSG,"账户名已存在");
        }
        return new JSONObject(res).toString();
    }

    /*
     * 登出 返回过期的token
     * return { "status":"success/failed","token":"new token"}
     * */
    @Override
    public String logout(HttpServletRequest request) {
        Map<String,String> res = new HashMap<>();
        String token = request.getHeader("token"); //获取token
        String newToken = tokenUtil.destroyToken(token); //设置过期
        if(newToken!=null){
            res.put(STATUS,STATUS_SUCCESS);
            res.put(TOKEN,newToken);
        }else{
            res.put(STATUS,STATUS_FAILED);
        }
        return new JSONObject(res).toString();
    }

    /*
     * 验证
     * return { "status":"success"}
     * */
    @Override
    public String checkToken() {
        //若token验证失败，拦截器会返回401码和错误信息
        Map<String,String> res = new HashMap<>();
        res.put(STATUS,STATUS_SUCCESS);
        return new JSONObject(res).toString();
    }



    /*
     * 修改用户数据
     * return { "status":"success/failed"}
     * */
    @Override
    public String update(HttpServletRequest request,String username,String password,String school,String major) {
        Map<String,String> res = new HashMap<>();
        int id = Integer.valueOf(request.getHeader("id"));
        User existUser = mapper.getUserByName(username);
        if(existUser!=null){
            res.put(STATUS,STATUS_FAILED);
            res.put(MSG,"用户名已存在");
            return new JSONObject(res).toString();
        }
        User user = mapper.getUserById(id);
        if(username==null)username = user.getUsername();
        if(password==null)password = user.getPassword();
        if(school ==null) school = user.getSchool();
        if(major ==null) major = user.getMajor();
        System.out.println(id+":"+username+":"+password+":"+school+":"+major);
        int updateRes = mapper.update(id,username,password,school,major);
        if(updateRes>0){
            res.put(STATUS,STATUS_SUCCESS);
        }else{
            res.put(STATUS,STATUS_FAILED);
        }
        return new JSONObject(res).toString();
    }

    //通过token获取用户信息
    @Override
    public User getInfo(HttpServletRequest request) {
        int id = Integer.valueOf(request.getHeader("id"));
        User user = mapper.getUserById(id);
        return user;
    }

}
