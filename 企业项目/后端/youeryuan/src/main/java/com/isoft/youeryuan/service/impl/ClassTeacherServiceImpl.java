package com.isoft.youeryuan.service.impl;

import com.isoft.youeryuan.entity.ClassTeacher;
import com.isoft.youeryuan.dao.ClassTeacherDao;
import com.isoft.youeryuan.entity.ClassInfo;
import com.isoft.youeryuan.entity.Employee;
import com.isoft.youeryuan.service.ClassTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClassTeacherServiceImpl implements ClassTeacherService {
    @Autowired
    ClassTeacherDao classTeacherDao ;
    @Override
    public List<ClassTeacher> getAll() {
        return classTeacherDao.getAll();
    }

    @Override
    public boolean add(String cid, String tid) {
        return classTeacherDao.add(cid , tid) > 0 ? true : false;
    }

    @Override
    public boolean del(String cid) {
        return classTeacherDao.del(cid) > 0 ? true : false;
    }

    @Override
    public boolean updateTeacher(String cid, String tid) {
        return classTeacherDao.update(cid , tid) > 0 ? true : false;
    }

    @Override
    public List<ClassInfo> getClassNoTeacher() {
        return classTeacherDao.getClassNoTeacher();
    }

    @Override
    public List<Employee> getTeacherNoClass() {
        return classTeacherDao.getTeacherNoClass() ;
    }
}
