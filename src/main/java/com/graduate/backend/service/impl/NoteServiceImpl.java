package com.graduate.backend.service.impl;

import com.graduate.backend.mapper.NoteMapper;
import com.graduate.backend.pojo.Note;
import com.graduate.backend.pojo.Response;
import com.graduate.backend.service.NoteService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper mapper;

    //获取uid
    private int getId(HttpServletRequest request){
        String id = request.getHeader("id");
        Pattern pattern = Pattern.compile("[0-9]*");
        if(pattern.matcher(id).matches()){
            return Integer.valueOf(id);
        }else return -1;
    }

    //返回note列表
    @Override
    public List<Note> getNotes(HttpServletRequest request) {
        int uid = getId(request);
        return mapper.getNotes(uid);
    }

    //返回response
    @Override
    public Response addNote(HttpServletRequest request, String title, String content) {
        int uid = getId(request);
        Integer insert = mapper.insert(uid,title,content);
        Response res = new Response();
        if(insert >0){
            res.setSuccess();
            res.setMsg("添加成功");
        }else{
            res.setFailed();
            res.setMsg("添加失败");
        }

        return res;
    }

    //返回response
    @Override
    public Response deleteNote(HttpServletRequest request, int nid) {
        int uid = getId(request);
        Integer insert = mapper.delete(uid,nid);
        Response res = new Response();
        if(insert >0){
            res.setSuccess();
            res.setMsg("删除成功");
        }else{
            res.setFailed();
            res.setMsg("删除失败");
        }
        return res;
    }

    @Override
    public Response updateNote(HttpServletRequest request, int nid, String title, String content) {
        int uid = getId(request);
        Integer insert = mapper.update(uid,nid,title,content);
        Response res = new Response();
        if(insert >0){
            res.setSuccess();
            res.setMsg("更新成功");
        }else{
            res.setFailed();
            res.setMsg("更新失败");
        }
        return res;
    }
}
