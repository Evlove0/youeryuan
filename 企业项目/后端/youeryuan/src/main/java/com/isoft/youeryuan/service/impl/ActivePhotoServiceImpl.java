package com.isoft.youeryuan.service.impl;

import com.isoft.youeryuan.dao.ActivePhotoDao;
import com.isoft.youeryuan.entity.ActivePhoto;
import com.isoft.youeryuan.service.ActivePhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivePhotoServiceImpl implements ActivePhotoService {
    @Autowired
    ActivePhotoDao activePhotoDao;
    @Override
    public boolean addPhoto(ActivePhoto activePhoto) {
       return activePhotoDao.insert(activePhoto) >0 ? true : false;
    }

    @Override
    public int deleteMorePhoto(List<Integer> ids) {
        return activePhotoDao.deleteBatchIds(ids);
    }

    @Override
    public boolean deletePhoto(int id) {
        return activePhotoDao.deleteById(id) >0 ? true : false;
    }

    @Override
    public boolean myAddActiePhoto(String photo,int aid) {
        System.out.println(photo+"service");return activePhotoDao.myAddActiePhoto(photo,aid);
    }

    @Override
    public List<ActivePhoto> selectByAid(Integer id) {
        System.out.println("service_SelectByAId:"+id);
        return activePhotoDao.selectPhotoByAid(id);
    }

    @Override
    public int totalCount(Integer id) {
        System.out.println("service_totalCount:"+id);
        return activePhotoDao.totalCount(id);
    }
}
