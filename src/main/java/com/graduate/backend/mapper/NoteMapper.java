package com.graduate.backend.mapper;

import com.graduate.backend.pojo.Note;
import com.graduate.backend.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

//@Mapper
@Repository
public interface NoteMapper {

//     查找笔记列表
    List<Note> getNotes(@Param("uid") int uid);

    //更新
    Integer update(@Param("uid")int uid, @Param("nid")int nid, @Param("title")String title,
                   @Param("content")String content);

    //插入
    Integer insert(@Param("uid")int uid, @Param("title")String title,
                   @Param("content")String content);

    //删除
    Integer delete(@Param("uid")int uid, @Param("nid")int nid);

}
