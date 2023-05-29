package com.graduate.backend.service;

import com.graduate.backend.pojo.Notification;
import com.graduate.backend.pojo.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

//日历事件接口类
public interface NotifyService {

    // 获取事件列表
    public List<Notification> getAllNotificationList(HttpServletRequest request);

    // 通过时间获取事件列表
    //传入时间格式yyyy-mm-dd
    public List<Notification> getNotificationListByTime(HttpServletRequest request, String date);

    // 添加事件
    //传入时间格式yyyy-MM-dd HH:mm:ss
    public Response addNotification(HttpServletRequest request, String title, String des,
                                    String type, String startTime, String endTime);

    //删除课程
    public Response deleteNotification(HttpServletRequest request, int notifyId);



}
