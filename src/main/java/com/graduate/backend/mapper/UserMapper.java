package com.graduate.backend.mapper;

import com.graduate.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

//@Mapper
@Repository
public interface UserMapper {
    /**
     * 通过id查找用户
     * */
    public User getUserById(@Param("id") int id);

}
