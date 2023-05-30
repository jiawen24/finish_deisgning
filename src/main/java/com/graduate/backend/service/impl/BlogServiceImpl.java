package com.graduate.backend.service.impl;

import com.graduate.backend.mapper.BlogMapper;
import com.graduate.backend.mapper.UserMapper;
import com.graduate.backend.pojo.Blog;
import com.graduate.backend.pojo.Response;
import com.graduate.backend.pojo.User;
import com.graduate.backend.service.BlogService;
import com.graduate.backend.util.StrUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BlogMapper blogMapper;

    private static String[] majors = new String[]{"哲学","经济学","法学","社会学"
    ,"汉语言文学","数学与应用数学","计算机科学与技术","软件工程","网络工程","其他"};

    @Override
    public List<String> getMajorList() {
        return Arrays.asList(majors);
    }

    @Override
    public List<Blog> getBlogListByMajor(String major) {
        return blogMapper.getBlogsByMajor(major);
    }

    @Override
    public Response addBlog(HttpServletRequest request, String title, String content) {
        int uid = StrUtil.getId(request);
        User user = userMapper.getUserById(uid);
        Blog blog = new Blog(-1,uid,title,user.getMajor(),content,user.getUsername());
        int insertRes = blogMapper.insert(blog);
        Response response = new Response();
        if(insertRes>0){
            response.setSuccess();
            response.setMsg(new JSONObject(blog).toString());
        }else{
            response.setFailed();
            response.setMsg("发送失败");
        }
        return response;
    }
}
