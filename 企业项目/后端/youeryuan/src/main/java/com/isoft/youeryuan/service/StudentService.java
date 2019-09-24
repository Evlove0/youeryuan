package com.isoft.youeryuan.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.isoft.youeryuan.entity.Student;

import java.util.List;

public interface StudentService {
    boolean add(Student s) ;

    /**
     * 退园
     * @param id
     * @return
     */
    boolean leave(Integer id) ;

    /**
     * 退园
     * @param ids
     * @return
     */
    int leave(List<Integer> ids) ;

    boolean update(Student s) ;

    Student getById(Integer id) ;

    List<Student> getByClassid(String classid) ;

    IPage<Student> getPageData(Integer pageNumber, Integer pageSize, String cid, String nameKey, String gender, String birthYear, Integer state) ;
}
