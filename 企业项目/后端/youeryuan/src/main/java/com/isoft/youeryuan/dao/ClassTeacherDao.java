package com.isoft.youeryuan.dao;

import com.isoft.youeryuan.entity.ClassTeacher;
import com.isoft.youeryuan.entity.ClassInfo;
import com.isoft.youeryuan.entity.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassTeacherDao {
    @Select("select * from v_classteacher order by cid")
    List<ClassTeacher> getAll() ;

    @Insert("insert into tb_classteacher values(null,#{cid},#{tid})")
    int add(@Param("cid") String cid, @Param("tid") String tid) ;

    @Delete("delete from tb_classteacher where cid=#{cid}")
    int del(String cid) ;

    @Update("update tb_classteacher set tid=#{tid} where cid=#{cid}")
    int update(@Param("cid") String cid, @Param("tid") String tid);

    @Select("select cid , cname from tb_class where cid not in(select cid from v_classteacher)")
    List<ClassInfo> getClassNoTeacher() ;

    @Select("select tid , tname from tb_employee where role=0 and state in(0,2) and tid not in(select tid from v_classteacher)")
    List<Employee> getTeacherNoClass();
}
