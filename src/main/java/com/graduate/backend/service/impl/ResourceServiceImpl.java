package com.graduate.backend.service.impl;

import com.graduate.backend.config.FileConfig;
import com.graduate.backend.mapper.ResourceMapper;
import com.graduate.backend.pojo.Resource;
import com.graduate.backend.service.ResourceService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceServiceImpl implements ResourceService {
    private final String STATUS_FAILED = "failed";
    private final String STATUS_SUCCESS = "success";
    private final String STATUS = "status";
    private final String MSG = "msg";

    @Autowired
    private ResourceMapper mapper;

    /*
     * 获取文件列表
     * return resource列表
     * */
    @Override
    public List<Resource> getFiles(HttpServletRequest request) {
        int uid = Integer.valueOf(request.getHeader("id"));
        return mapper.getFiles(uid);
    }

    /*
     * 上传文件
     * return { "status":"success/failed","msg":"上传成功/失败"}
     * */
    @Override
    public String uploadFile(HttpServletRequest request, MultipartFile file) {
        Map<String,String> res = new HashMap<>();
        if(file.isEmpty()){
            res.put(STATUS,STATUS_FAILED);
            res.put(MSG,"上传文件为空");
            return new JSONObject(res).toString();
        }
        try{
            //保存到本地文件
            int uid = Integer.valueOf(request.getHeader("id"));
            File parent = new File(FileConfig.location);
            String parentPath = parent.getCanonicalPath();
            String fileName = file.getOriginalFilename();
            String savePath = uid+"-"+fileName; //保存路径为"id-name"
            file.transferTo(new File(parentPath+"/"+savePath));
            //插入数据库
            mapper.insert(uid,fileName,savePath);
            res.put(STATUS,STATUS_SUCCESS);
            res.put(MSG,"上传成功");
            return new JSONObject(res).toString();
        }catch (IOException e){
            res.put(STATUS,STATUS_FAILED);
            res.put(MSG,"上传失败");
            return new JSONObject(res).toString();
        }
    }

    /*
     * 删除文件
     * return { "status":"success/failed","msg":"unknown"}
     * */
    @Override
    public String deleteFile(HttpServletRequest request, int fid) {
        int uid = Integer.valueOf(request.getHeader("id"));
        Map<String,String> res = new HashMap<>();

        try {
            Resource resource = mapper.getFile(uid,fid);
            if(resource!=null){
                File parent = new File(FileConfig.location);
                System.out.println(parent.getCanonicalPath());
                File file = new File(parent,resource.getFilePath());
                if(file.exists()) file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int insertRes = mapper.delete(uid,fid);

        if(insertRes>0){ //删除成功
            res.put(STATUS,STATUS_SUCCESS);
            res.put(MSG,STATUS_SUCCESS);
        }else{ //删除失败
            res.put(STATUS,STATUS_FAILED);
            res.put(MSG,"unknown");
        }
        return new JSONObject(res).toString();
    }
}
