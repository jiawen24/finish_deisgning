package com.graduate.backend.service;

import com.graduate.backend.pojo.Note;
import com.graduate.backend.pojo.Resource;
import com.graduate.backend.pojo.Response;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//笔记接口类
public interface NoteService {
    //获取笔记列表
    List<Note> getNotes(HttpServletRequest request);

    //添加笔记
    Response addNote(HttpServletRequest request, String title, String content);

    //删除笔记
    Response deleteNote(HttpServletRequest request,int nid);

    //更新笔记
    Response updateNote(HttpServletRequest request,int nid,String title,String content);
}
