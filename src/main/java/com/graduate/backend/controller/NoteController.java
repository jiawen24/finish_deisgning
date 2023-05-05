package com.graduate.backend.controller;

import com.graduate.backend.pojo.Note;
import com.graduate.backend.pojo.Response;
import com.graduate.backend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class NoteController {
    @Autowired
    private NoteService service;

    //获取笔记
    @RequestMapping(value = "note/getList",method = RequestMethod.GET)
    public List<Note> login(HttpServletRequest request)
    {
        return service.getNotes(request);
    }

    //添加笔记
    @RequestMapping(value = "note/add",method = RequestMethod.POST)
    public Response login(HttpServletRequest request,@RequestParam("title")String title,@RequestParam("content")String content)
    {
        return service.addNote(request, title, content);
    }

    //删除笔记
    @RequestMapping(value = "note/delete",method = RequestMethod.POST)
    public Response login(HttpServletRequest request,@RequestParam("nid")int nid)
    {
        return service.deleteNote(request,nid);
    }

    //更新笔记
    @RequestMapping(value = "note/update",method = RequestMethod.POST)
    public Response login(HttpServletRequest request,@RequestParam("nid")int nid,@RequestParam("title")String title,@RequestParam("content")String content)
    {
        return service.updateNote(request, nid, title, content);
    }

}
