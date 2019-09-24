package com.isoft.youeryuan.service.impl;

import com.isoft.youeryuan.dao.ClassInfoDao;
import com.isoft.youeryuan.entity.ClassInfo;
import com.isoft.youeryuan.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClassInfoServiceImpl implements ClassInfoService {
    @Autowired
    ClassInfoDao classInfoDao ;

    @Override
    public boolean add(ClassInfo info) {
        return classInfoDao.insert(info) > 0 ? true : false;
    }

    @Override
    public boolean del(Integer id) {
        return classInfoDao.deleteById(id) > 0 ? true : false;
    }

    @Override
    public boolean delById(Integer id) {

            return classInfoDao.deleteById(id) > 0 ? true : false;
    }

    @Override
    public int delMore(List<Integer> ids) {
        return classInfoDao.deleteBatchIds(ids) ;
    }

    @Override
    public List<ClassInfo> getAll() {
        return classInfoDao.selectList(null);
    }
}
