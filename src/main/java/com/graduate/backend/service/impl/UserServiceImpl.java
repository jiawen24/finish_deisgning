package com.graduate.backend.service.impl;

import com.graduate.backend.config.FileConfig;
import com.graduate.backend.mapper.UserMapper;
import com.graduate.backend.pojo.Response;
import com.graduate.backend.pojo.User;
import com.graduate.backend.service.UserService;
import com.graduate.backend.util.TokenUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
    public Response register(String name, String password) {
        Response res = new Response();
        User user = mapper.getUserByName(name);
        if(user==null){
            int insertRes = mapper.insert(name, password);
            if(insertRes>0){ //插入成功
                res.setSuccess();
                res.setMsg("注册成功");
            }else{ //插入失败
                res.setFailed();
                res.setMsg("注册失败");
            }
        }else{ //用户名已存在
            res.setFailed();
            res.setMsg("账户名已存在");
        }
        return res;
    }

    //此方法返回过期token ->但是不如直接在前端把旧token删除就行了。
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
    public Response checkToken() {
        //若token验证失败，拦截器会返回401码和错误信息
        Response res = new Response();
        res.setSuccess();
        return res;
    }



    /*
     * 修改用户数据
     * return { "status":"success/failed"}
     * */
    @Override
    public Response update(HttpServletRequest request,String username,String password,String school,String major) {
        Response res = new Response();
        int id = Integer.valueOf(request.getHeader("id"));
        User existUser = mapper.getUserByName(username);
        if(existUser!=null){
            res.setFailed();
            res.setMsg("用户名已存在");
            return res;
        }
        User user = mapper.getUserById(id);
        if(username==null)username = user.getUsername();
        if(password==null)password = user.getPassword();
        if(school ==null) school = user.getSchool();
        if(major ==null) major = user.getMajor();
        //插入数据库
        int updateRes = mapper.update(id,username,password,school,major);
        if(updateRes>0){
            res.setSuccess();
            res.setMsg("更新成功");
        }else{
            res.setFailed();
            res.setMsg("更新失败");
        }
        return res;
    }

    //通过token获取用户信息
    @Override
    public User getInfo(HttpServletRequest request) {
        int id = Integer.valueOf(request.getHeader("id"));
        User user = mapper.getUserById(id);
        return user;
    }

    @Override
    public Response uploadAvatar(HttpServletRequest request, MultipartFile file) {
        Response res = new Response();
        if(file.isEmpty()){
            res.setFailed();
            res.setMsg("上传文件为空");
            return res;
        }
        try{
            int id = Integer.valueOf(request.getHeader("id"));
            //保存到本地文件
            File parent = new File(FileConfig.location);
            String parentPath = parent.getCanonicalPath();
            String saveName = id+"-avatar.jpg"; //保存路径为用户"id-avatar"
            file.transferTo(new File(parentPath+"/"+saveName));
            //插入数据库
            mapper.updateAvatar(id,saveName);
            res.setSuccess();
            res.setMsg("上传成功");
            return res;
        }catch (IOException e){
            res.setFailed();
            res.setMsg("上传失败");
            return res;
        }
    }
}
