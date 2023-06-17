package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select id,username,password from user where username = #{username} and password = #{password}")
    User getByUsernameAndPassword(User user);
}
