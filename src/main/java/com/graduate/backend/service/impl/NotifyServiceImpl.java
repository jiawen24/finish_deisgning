package com.graduate.backend.service.impl;


import com.graduate.backend.mapper.NotifyMapper;
import com.graduate.backend.pojo.Notification;

import com.graduate.backend.pojo.Response;
import com.graduate.backend.service.NotifyService;
import com.graduate.backend.util.StrUtil;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class NotifyServiceImpl implements NotifyService {
    SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private NotifyMapper mapper;


    @Override
    public List<Notification> getAllNotificationList(HttpServletRequest request) {
        int uid = StrUtil.getId(request);
        return mapper.getAll(uid);
    }

    //传入date格式 yyyy-mm-dd
    @Override
    public List<Notification> getNotificationListByTime(HttpServletRequest request, String date) {
        int uid = StrUtil.getId(request);
        Date today = getDateByString(date,sdfDay);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE,1);
        Date tomorrow = calendar.getTime();
        return mapper.getNotifyByTime(uid,today,tomorrow);
    }

    @Override
    public Response addNotification(HttpServletRequest request, String title, String des, String type, String startTime, String endTime) {
        Date start = getDateByString(startTime,sdfTime);
        Date end = getDateByString(endTime,sdfTime);
        int uid = StrUtil.getId(request);
        Response response = new Response();
        Notification notification = new Notification(uid,-1,type,title,des,start,end);
        int insertRes = mapper.insert(notification);
        if(insertRes>0){
            response.setSuccess();
            JSONObject json = new JSONObject(notification);
            json.put("startTime",sdfTime.format(notification.getStartTime()));
            json.put("endTime", sdfTime.format(notification.getEndTime()));
            response.setMsg(json.toString());
        }else{
            response.setFailed();
            response.setMsg("添加失败");
        }
        return response;
    }

    @Override
    public Response deleteNotification(HttpServletRequest request, int notifyId) {
        int uid = StrUtil.getId(request);
        Response response = new Response();
        int deleteRes = mapper.delete(uid,notifyId);
        if(deleteRes>0){
            response.setSuccess();
            response.setMsg("删除成功");
        }else{
            response.setFailed();
            response.setMsg("删除失败");
        }
        return response;
    }

    //String转换为Date
    private Date getDateByString(String str,SimpleDateFormat format)
    {
        Date date = null; //初始化date
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
