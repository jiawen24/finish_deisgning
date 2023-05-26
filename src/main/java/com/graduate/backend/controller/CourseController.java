package com.graduate.backend.controller;

import com.graduate.backend.pojo.Course;
import com.graduate.backend.pojo.Response;
import com.graduate.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService service;

    //获取课程列表
    @RequestMapping(value = "course/getList",method = RequestMethod.GET)
    public List<Course> getList(HttpServletRequest request)
    {
        return service.getCourseList(request);
    }

    //添加课程
    @RequestMapping(value = "course/add",method = RequestMethod.POST)
    public Response addCourse(HttpServletRequest request,
                                 @RequestParam("courseName")String courseName, @RequestParam("teacherName")String teacherName,
                                 @RequestParam("address")String address,@RequestParam("dayWeek")int dayWeek,
                                 @RequestParam("startSlice")int startSlice, @RequestParam("endSlice")int endSlice,
                                 @RequestParam("startWeek")int startWeek, @RequestParam("endWeek")int endWeek)
    {
        return service.addCourse(request,courseName, teacherName, address, dayWeek, startSlice, endSlice, startWeek, endWeek);
    }

    //更新课程
    @RequestMapping(value = "course/update",method = RequestMethod.POST)
    public Response updateCourse(HttpServletRequest request,@RequestParam("tableId")int tableId,
                                 @RequestParam("courseName")String courseName, @RequestParam("teacherName")String teacherName,
                                 @RequestParam("address")String address,@RequestParam("dayWeek")int dayWeek,
                                 @RequestParam("startSlice")int startSlice, @RequestParam("endSlice")int endSlice,
                                 @RequestParam("startWeek")int startWeek, @RequestParam("endWeek")int endWeek)
    {
        return service.updateCourse(request, tableId, courseName, teacherName, address, dayWeek, startSlice, endSlice, startWeek, endWeek);
    }

    //删除课程
    @RequestMapping(value = "course/delete",method = RequestMethod.POST)
    public Response deleteFile(HttpServletRequest request,@RequestParam("tableId") int tid){
        return service.deleteCourse(request, tid);
    }

}
