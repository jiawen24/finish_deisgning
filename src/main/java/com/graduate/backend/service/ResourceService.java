package com.graduate.backend.service;

import com.graduate.backend.pojo.Resource;
import com.graduate.backend.pojo.Response;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

//资源文件接口类
public interface ResourceService {
    //获取文件列表
    List<Resource> getFiles(HttpServletRequest request);

    //上传文件
    Response uploadFile(HttpServletRequest request, MultipartFile file);

    //删除文件
    Response deleteFile(HttpServletRequest request,int fid);
}
