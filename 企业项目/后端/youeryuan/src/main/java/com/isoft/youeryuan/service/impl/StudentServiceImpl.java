package com.isoft.youeryuan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.youeryuan.dao.StudentDao;
import com.isoft.youeryuan.entity.Student;
import com.isoft.youeryuan.service.StudentService;
import com.isoft.youeryuan.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao ;
    @Override
    public boolean add(Student s) {
        s.setSid(UUID.randomUUID().toString());
        s.setState(Student.STATE_IN);
        return studentDao.insert(s) >0 ? true : false;
    }

    @Override
    public boolean leave(Integer id) {
        Student s = new Student() ;
        s.setId(id);
        s.setState(Student.STATE_LEAVE);
        return studentDao.updateById(s) > 0 ? true : false ;
    }

    @Override
    public int leave(List<Integer> ids) {
        UpdateWrapper<Student> wrapper = new UpdateWrapper<>() ;
        wrapper.set(Student.COLUMN_STATE, Student.STATE_LEAVE) ;   // set state=1
        wrapper.in(Student.COLUMN_ID , ids) ;   // where id in(ids)
        return studentDao.update(null , wrapper) ;
    }

    @Override
    public boolean update(Student s) {
        return studentDao.updateById(s) > 0 ? true : false ;
    }

    @Override
    public Student getById(Integer id) {
        return studentDao.selectById(id) ;
    }

    @Override
    public List<Student> getByClassid(String classid) {
        QueryWrapper<Student> wrapper = new QueryWrapper<>() ;
        wrapper.eq(Student.COLUMN_CLASSID , classid) ;
        return studentDao.selectList(wrapper) ;
    }

    @Override
    public IPage<Student> getPageData(Integer pageNumber, Integer pageSize, String cid, String nameKey, String gender, String enterDate , Integer state) {
        System.out.println(pageNumber + "," + pageSize);
        QueryWrapper<Student> wrapper = new QueryWrapper<>() ;
        if(!StringUtil.isEmpty(nameKey)) {
            wrapper.like(Student.COLUMN_NAME , nameKey) ;
        }
        if(! StringUtil.isEmpty(cid)) {
            wrapper.eq(Student.COLUMN_CLASSID , cid) ;
        }
        if(!StringUtil.isEmpty(gender)) {
            wrapper.eq(Student.COLUMN_GENDER , gender) ;
        }
        if(!StringUtil.isEmpty(enterDate)) {
            wrapper.like(Student.COLUMN_ENTERDATE , enterDate) ;
        }
        if(null != state && (state == Student.STATE_LEAVE || state == Student.STATE_IN)) {
            wrapper.eq(Student.COLUMN_STATE , state) ;
        }
        IPage<Student> page = new Page<>(pageNumber , pageSize) ;
        return studentDao.selectPage(page , wrapper) ;
    }
}
