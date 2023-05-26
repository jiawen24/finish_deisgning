package com.graduate.backend.mapper;

import com.graduate.backend.pojo.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
@Repository
public interface CourseMapper {

//     通过用户id获取课程
    public List<Course> getCourse(@Param("userId") int userId);

//    删除课程
    public Integer delete(@Param("userId") int userId,@Param("tableId") int tableId);

//    添加课程
    public Integer insert(@Param("course") Course course);

    public Integer update( @Param("course") Course course);

//    修改课程
//    public Integer update(@Param("userId") int userId,@Param("tableId") int tableId,
//                          @Param("courseName") String courseName, @Param("teacherName") String teacherName,
//                          @Param("address") String address, @Param("dayWeek") int  dayWeek,
//                          @Param("startSlice") int  startSlice, @Param("endSlice") int  endSlice,
//                          @Param("startWeek") int startWeek, @Param("endWeek") int  endWeek);

}
