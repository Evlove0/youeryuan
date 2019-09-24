package com.isoft.youeryuan;

import com.isoft.youeryuan.dao.EmployeeDao;
import com.isoft.youeryuan.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.isoft.youeryuan.dao")
public class YoueryuanApplicationTests {
    @Autowired
    EmployeeDao employeeDao ;

    @Test
    public void contextLoads() {
//        System.out.println(employeeDao.getById(1));
//        int update(@Param("id") Integer id , @Param("email") String email , @Param("mobile") String mobile , @Param("address") String address , @Param("photo") String photo) ;

//        System.out.println(employeeDao.update(1 , "rounding@qq.com" , "123" , null , null));
        /*
        @Insert("insert into tb_employee values(" +
            "null ,uuid() , #{tname} , #{pass} , 0 , #{nation} , #{education} , #{school} , #{gender} , #{email} , #{birth} ,#{mobile},#{address},#{photo},#{departure},null,2" +
            ")")
    int add(Employee employee) ;
    */
//        Employee employee = new Employee("X1" , "123" , 1 , "汉" , "小学" , "精武镇宠物培训学院" , "男" , "x1@sina.com" , new Date(), "138" , null , null) ;
//        System.out.println(employeeDao.add(employee));

//        List<Employee> select(Integer offset , Integer rowCount , Integer role , String gender , String nameKey , String mobileKey , Integer state) ;

        System.out.println(employeeDao.select(0 , 2 , null , null , "1" , null , null));
//        int selectCount(Integer role , String gender , String nameKey , String mobileKey , Integer state) ;

        System.out.println(employeeDao.selectCount(null , null , "1" , null , null));
    }

}
