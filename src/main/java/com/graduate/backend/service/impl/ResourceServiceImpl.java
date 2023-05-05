package com.graduate.backend.service.impl;

import com.graduate.backend.config.FileConfig;
import com.graduate.backend.mapper.ResourceMapper;
import com.graduate.backend.pojo.Resource;
import com.graduate.backend.pojo.Response;
import com.graduate.backend.service.ResourceService;
import com.graduate.backend.util.StrUtil;
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


    @Autowired
    private ResourceMapper mapper;

    /*
     * 获取文件列表
     * return resource列表
     * */
    @Override
    public List<Resource> getFiles(HttpServletRequest request) {
        int uid = StrUtil.getId(request);
        return mapper.getFiles(uid);
    }

    /*
     * 上传文件
     * return { "status":"success/failed","msg":"上传成功/失败"}
     * */
    @Override
    public Response uploadFile(HttpServletRequest request, MultipartFile file) {
        Response res = new Response();
        if(file.isEmpty()){
            res.setFailed();
            res.setMsg("上传文件为空");
            return res;
        }
        try{
            //保存到本地文件
            int uid = StrUtil.getId(request);
            File parent = new File(FileConfig.location);
            String parentPath = parent.getCanonicalPath();
            String fileName = file.getOriginalFilename();
            String savePath = uid+"-"+fileName; //保存路径为"id-name"
            file.transferTo(new File(parentPath+"/"+savePath));
            //插入数据库
            mapper.insert(uid,fileName,savePath);
            res.setSuccess();
            res.setMsg("上传成功");
            return res;
        }catch (IOException e){
            res.setFailed();
            res.setMsg("上传失败");
            return res;
        }
    }

    /*
     * 删除文件
     * return { "status":"success/failed","msg":"unknown"}
     * */
    @Override
    public Response deleteFile(HttpServletRequest request, int fid) {
        int uid = StrUtil.getId(request);
        Response res = new Response();
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
            res.setSuccess();
            res.setMsg("删除成功");
        }else{ //删除失败
            res.setFailed();
            res.setMsg("删除失败");
        }
        return res;
    }
}
