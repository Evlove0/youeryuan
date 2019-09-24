package com.isoft.youeryuan.dao;

import com.isoft.youeryuan.entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleDao {
    @Select("select * from tb_role")
    List<Role> getAll() ;

    @Insert("insert into tb_role values(null , #{role})")
    int add(String role) ;
}
