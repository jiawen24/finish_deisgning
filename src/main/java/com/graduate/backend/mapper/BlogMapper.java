package com.graduate.backend.mapper;

import com.graduate.backend.pojo.Blog;
import com.graduate.backend.pojo.Note;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
@Repository
public interface BlogMapper {

//     查找博客列表
    List<Blog> getBlogsByMajor(@Param("major")String major);

    //插入
    Integer insert( @Param("blog")Blog blog);

}
