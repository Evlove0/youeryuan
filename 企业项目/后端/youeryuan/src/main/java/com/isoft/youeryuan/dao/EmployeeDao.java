package com.isoft.youeryuan.dao;

import com.isoft.youeryuan.entity.Employee;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 针对tb_employee表的CRUD操作
 */
@Mapper
public interface EmployeeDao {

    @Insert("insert into tb_employee values(" +
            "null ,uuid() , #{tname} , #{pass} , #{role} , #{nation} , #{education} , #{school} , #{gender} , #{email} , #{birth} ,#{mobile},#{address},#{photo},#{hiredate},null,2" +
            ")")
    int add(Employee employee) ;

    @Update("update tb_employee set state=#{state} where id=#{id}")
    int changeState(Integer id , Integer state) ;

    @Update("update tb_employee set state=1,departure=now() where id=#{id}")
    int leave(Integer id) ;

    @Select("select * from tb_employee where tid=#{tid}")
    Employee getByTid(String tid) ;

    @Select("select id , tid , tname from tb_employee where state in (0,2) and role=0")
    List<Employee> getAllTeacher();

    int update(@Param("id") Integer id , @Param("email") String email , @Param("mobile") String mobile , @Param("address") String address , @Param("photo") String photo) ;

    List<Employee> select(Integer offset , Integer rowCount , Integer role , String gender , String nameKey , String mobileKey , Integer state) ;

    int selectCount(Integer role , String gender , String nameKey , String mobileKey , Integer state) ;
}
