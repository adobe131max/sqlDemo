package com.example.mapper;

import com.example.pojo.Dependency;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DependencyMapper {

//    @Select("select id,`key`,`value` from sql2023.dependency")
    List<Dependency> list(String key,String value);

    @Select("select id,`key`,`value` from sql2023.dependency where id = #{id}")
    Dependency selectById(Integer id);

    @Delete("delete from sql2023.dependency where id = #{id}")
    void deleteById(Integer id);

    void deleteByIds(List<Integer> ids);

    void update(Dependency dependency);

    @Insert("insert into sql2023.dependency(`key`, `value`) values (#{key},#{value})")
    void insert(Dependency dependency);
}
