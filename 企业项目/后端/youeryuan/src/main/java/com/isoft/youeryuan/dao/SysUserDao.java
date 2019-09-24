package com.isoft.youeryuan.dao;

import com.isoft.youeryuan.entity.SysUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 该接口描述针对系统管理员表要执行的数据库操作
 */
@Mapper
public interface SysUserDao {

    @Select("select * from tb_system where name=#{name} and pass=#{pass} and state=0")
    SysUser loginCheck(@Param("name") String name , @Param("pass") String pass);

    /**
     * 密码校验
     * @return  0--密码错误，1--密码正确
     */
    @Select("select count(*) from tb_system where id=#{id} and pass=#{pass}")
    int checkPass(@Param("id") Integer id, @Param("pass")String oldPass);
    /**
     * 修改密码
     */
    @Update("update tb_system set pass=#{pass} where id=#{id}")
    int repass(@Param("id")Integer id , @Param("pass")String newPass);

    @Update("update tb_system set photourl=#{photo} where id=#{id}")
    int rephoto(@Param("id")Integer id , @Param("photo")String photoUrl);

    @Insert("insert into tb_system values(null , #{name} , #{pass} , #{role} , now() , null , 0)")
    int add(@Param("name") String name , @Param("pass") String pass , @Param("role") String role);

    @Update("update tb_system set state=1 where id=#{id}")
    int delById(Integer id) ;

    @Select("select * from tb_system")
    List<SysUser> getAll();
}
