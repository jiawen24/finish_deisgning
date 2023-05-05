package com.graduate.backend.controller;

import com.graduate.backend.pojo.Resource;
import com.graduate.backend.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ResourceController {
    @Autowired
    private ResourceService service;
    //获取资源列表
    @RequestMapping(value = "resource/getList",method = RequestMethod.GET)
    public List<Resource> getList(HttpServletRequest request)
    {
        return service.getFiles(request);
    }

    //上传文件
    @RequestMapping(value = "resource/upload",method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest request,@RequestParam("file") MultipartFile file)
    {
        return service.uploadFile(request,file);
    }

    //删除文件
    @RequestMapping(value = "resource/delete",method = RequestMethod.POST)
    public String deleteFile(HttpServletRequest request,@RequestParam("fid") int fid){
        return service.deleteFile(request,fid);
    }

}
