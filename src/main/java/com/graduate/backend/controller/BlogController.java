package com.graduate.backend.controller;

import com.graduate.backend.pojo.Blog;
import com.graduate.backend.pojo.Course;
import com.graduate.backend.pojo.Response;
import com.graduate.backend.service.BlogService;
import com.graduate.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class BlogController {
    @Autowired
    private BlogService service;

    //获取博客分类列表
    @RequestMapping(value = "blog/getMajorList",method = RequestMethod.GET)
    public List<String> getMajorList()
    {
        return service.getMajorList();
    }

    //获取博客列表
    @RequestMapping(value = "blog/getBlogList",method = RequestMethod.GET)
    public List<Blog> getBlogListByMajor(@RequestParam("major")String major)
    {
        return service.getBlogListByMajor(major);
    }

    //添加课程
    @RequestMapping(value = "blog/add",method = RequestMethod.POST)
    public Response addCourse(HttpServletRequest request,
                              @RequestParam("tittle")String tittle,
                              @RequestParam("content")String content)
    {
        return service.addBlog(request, tittle, content);
    }


}
