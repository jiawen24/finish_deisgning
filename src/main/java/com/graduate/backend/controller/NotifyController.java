package com.graduate.backend.controller;

import com.graduate.backend.pojo.Notification;
import com.graduate.backend.pojo.Response;
import com.graduate.backend.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class NotifyController {
    @Autowired
    private NotifyService service;

    //获取所有事件列表
    @RequestMapping(value = "notify/getAllList",method = RequestMethod.GET)
    public List<Notification> getList(HttpServletRequest request)
    {
        return service.getAllNotificationList(request);
    }

    //获取通知事件列表
    @RequestMapping(value = "notify/getListByTime",method = RequestMethod.GET)
    public List<Notification> getListByTime(HttpServletRequest request,@RequestParam("date") String date)
    {
        return service.getNotificationListByTime(request,date);
    }

    //添加事件
    @RequestMapping(value = "notify/add",method = RequestMethod.POST)
    public Response addNotification(HttpServletRequest request,
                                 @RequestParam("type")String type,
                                 @RequestParam("title")String title,
                                 @RequestParam("description")String description,
                                 @RequestParam("startTime") String startTime,
                                 @RequestParam("endTime")String endTime)
    {
        return service.addNotification(request,title,description,type,startTime,endTime);
    }

    //删除事件
    @RequestMapping(value = "notify/delete",method = RequestMethod.POST)
    public Response deleteFile(HttpServletRequest request,@RequestParam("notifyId") int notifyId){
        return service.deleteNotification(request, notifyId);
    }

}
