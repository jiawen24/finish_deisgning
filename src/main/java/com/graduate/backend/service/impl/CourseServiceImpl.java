package com.graduate.backend.service.impl;

import com.graduate.backend.mapper.CourseMapper;
import com.graduate.backend.pojo.Course;
import com.graduate.backend.pojo.Response;
import com.graduate.backend.service.CourseService;
import com.graduate.backend.util.StrUtil;
import com.graduate.backend.util.TokenUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper mapper;

    @Override
    public List<Course> getCourseList(HttpServletRequest request) {
        int uid = StrUtil.getId(request);
        return mapper.getCourse(uid);
    }

    @Override
    public Response addCourse(HttpServletRequest request, String courseName, String teacherName, String address, int dayWeek, int startSlice, int endSlice, int startWeek, int endWeek) {
        Response response = new Response();
        int uid = StrUtil.getId(request);

        //检测课程是否有冲突
        List<Course> courseList = mapper.getCourse(uid);
        for(Course course :courseList)
        {
            if(dayWeek == course.getDayWeek()){
                if(isTimeOverlap(startSlice,endSlice,course.getStartSlice(),course.getEndSlice()))
                {
                    response.setFailed();
                    response.setMsg("课程有冲突");
                    return response;
                }
            }
        }
        Course course = new Course(uid,-1,courseName,teacherName,address,dayWeek,startSlice,endSlice,startWeek,endWeek);
        int insertRes = mapper.insert(course);
        if(insertRes>0){
            response.setSuccess();
            response.setMsg(new JSONObject(course).toString());
        }else{
            response.setFailed();
            response.setMsg("添加失败");
        }
        return response;
    }

    @Override
    public Response updateCourse(HttpServletRequest request, int tableId, String courseName, String teacherName, String address, int dayWeek, int startSlice, int endSlice, int startWeek, int endWeek) {
        Response response = new Response();
        int uid = StrUtil.getId(request);
       //检测课程是否有冲突
        List<Course> courseList = mapper.getCourse(uid);
        for(Course course :courseList)
        {
            if(tableId==course.getTableId())continue;//排除自身
            if(dayWeek == course.getDayWeek()){
                if(isTimeOverlap(startSlice,endSlice,course.getStartSlice(),course.getEndSlice()))
                {
                    response.setFailed();
                    response.setMsg("课程有冲突");
                    return response;
                }
            }
        }

        Course course = new Course(uid,tableId,courseName,teacherName,address,dayWeek,startSlice,endSlice,startWeek,endWeek);
        int updateRes = mapper.update(course);
        if(updateRes>0){
            response.setSuccess();
            response.setMsg("更新成功");
        }else{
            response.setFailed();
            response.setMsg("更新失败");
        }
        return response;
    }

    @Override
    public Response deleteCourse(HttpServletRequest request, int tableId) {
        Response response = new Response();
        int uid = StrUtil.getId(request);
        int deleteRes = mapper.delete(uid,tableId);
        if(deleteRes>0){
            response.setSuccess();
            response.setMsg("删除成功");
        }else{
            response.setFailed();
            response.setMsg("删除失败");
        }
        return response;
    }

    //两个时间段是否有交集
    private boolean isTimeOverlap(int start1,int end1,int start2,int end2){
        return (start1>=start2 && start1<=end2)||
                (end1>=start1 && end1<=end2)||
                (start1<=start2&&end1>=end2);
    }

}
