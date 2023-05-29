package com.graduate.backend.mapper;

import com.graduate.backend.pojo.Course;
import com.graduate.backend.pojo.Notification;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

//@Mapper
@Repository
public interface NotifyMapper {

    //     获取所有事件
    public List<Notification> getAll(@Param("userId")int userId);


    //     通过时间获取事件
    public List<Notification> getNotifyByTime(@Param("userId")int userId, @Param("start") Date start, @Param("end")Date end);

//    删除事件
    public Integer delete(@Param("userId") int userId, @Param("notifyId") int notifyId);

//    添加事件
    public Integer insert(@Param("notification") Notification notification);




}
