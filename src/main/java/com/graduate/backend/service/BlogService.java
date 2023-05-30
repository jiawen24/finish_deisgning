package com.graduate.backend.service;

import com.graduate.backend.pojo.Blog;
import com.graduate.backend.pojo.Course;
import com.graduate.backend.pojo.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//课程表接口类
public interface BlogService {
    //获取专业分类
    public List<String> getMajorList();

    // 获取博客
    public List<Blog> getBlogListByMajor(String major);

    // 添加博客
    public Response addBlog(HttpServletRequest request,String title,String content);

}
