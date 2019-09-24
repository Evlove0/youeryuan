package com.isoft.youeryuan.service;

import com.isoft.youeryuan.entity.ClassTeacher;
import com.isoft.youeryuan.entity.ClassInfo;
import com.isoft.youeryuan.entity.Employee;

import java.util.List;

public interface ClassTeacherService {
    /**
     * 获取所有班级授课信息
     * @return
     */
    List<ClassTeacher> getAll();

    /**
     * 为某班级设置授课教师
     */
    boolean add(String cid, String tid) ;

    /**
     * 删除某个班级及授课教师
     * @param cid
     * @return
     */
    boolean del(String cid) ;

    /**
     * 某个班级更换教师
     * @param cid
     * @param tid
     * @return
     */
    boolean updateTeacher(String cid, String tid) ;

    /**
     * 获取没有分配教师的班级
     * @return
     */
    List<ClassInfo> getClassNoTeacher();

    /**
     * 获取没有分配班级的在职教师
     */
    List<Employee> getTeacherNoClass();
}
