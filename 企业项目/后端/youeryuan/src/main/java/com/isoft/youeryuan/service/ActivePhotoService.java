package com.isoft.youeryuan.service;

import com.isoft.youeryuan.entity.ActivePhoto;

import java.util.List;

public interface ActivePhotoService {
    boolean addPhoto(ActivePhoto activePhoto);

    int deleteMorePhoto(List<Integer> ids);

    boolean deletePhoto(int id);

    boolean myAddActiePhoto(String photo,int aid);

    List<ActivePhoto> selectByAid(Integer id);

    int totalCount(Integer id);



}
