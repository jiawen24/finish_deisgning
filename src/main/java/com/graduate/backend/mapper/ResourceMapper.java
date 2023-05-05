package com.graduate.backend.mapper;

import com.graduate.backend.pojo.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceMapper {

    //获取资源列表
    List<Resource> getFiles(@Param("uid")int uid);

    //获取单个文件
    Resource getFile(@Param("uid")int uid,@Param("fid")int fid);

    //插入
    Integer insert(@Param("uid")int uid,@Param("name")String name,@Param("path")String path);

    //删除
    Integer delete(@Param("uid")int uid,@Param("fid")int fid);
}
