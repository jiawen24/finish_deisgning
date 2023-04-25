package com.graduate.backend.mapper;

import com.graduate.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

//@Mapper
@Repository
public interface UserMapper {

//     通过id查找用户
    public User getUserById(@Param("id") int id);

//    通过账户密码查找用户
    public User getUserByNameAndPass(@Param("username")String username,@Param("password")String password);

//    用户账户名查找用户
    public User getUserByName(@Param("username")String username);

//    删除用户
    public Integer delete(@Param("id") int id);

//    添加用户
    public Integer insert(@Param("username")String username,@Param("password")String password);

//    修改用户信息
    public Integer update(@Param("id") int id,@Param("username")String username,@Param("password")String password,@Param("school")String school,@Param("major")String major);

//    更新用户头像
    public Integer updateAvatar(@Param("id") int id,@Param("avatar")String avatar);
}
