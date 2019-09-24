package com.isoft.youeryuan.service;

import com.isoft.youeryuan.entity.ClassInfo;

import java.util.List;

public interface ClassInfoService {

    boolean add(ClassInfo info) ;

    boolean del(Integer id) ;
    boolean delById(Integer id);

    int delMore(List<Integer> ids) ;

    List<ClassInfo> getAll() ;
}
