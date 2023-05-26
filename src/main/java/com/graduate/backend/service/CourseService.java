package com.graduate.backend.service;

import com.graduate.backend.pojo.Course;
import com.graduate.backend.pojo.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//课程表接口类
public interface CourseService {

    // 获取课程表
    public List<Course> getCourseList(HttpServletRequest request);

    // 添加课程
    public Response addCourse(HttpServletRequest request ,String courseName ,String teacherName,
                             String address,int dayWeek,int startSlice, int endSlice, int startWeek, int endWeek);

    //删除课程
    public Response deleteCourse(HttpServletRequest request,int tableId);

    //修改课程
    public Response updateCourse(HttpServletRequest request ,int tableId ,String courseName ,String teacherName,
                                 String address,int dayWeek,int startSlice, int endSlice, int startWeek, int endWeek);


}
