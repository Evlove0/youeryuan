package com.isoft.youeryuan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.youeryuan.dao.ActiveDao;
import com.isoft.youeryuan.entity.Active;
import com.isoft.youeryuan.service.ActiveService;
import com.isoft.youeryuan.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ActiveServiceImpl implements ActiveService {
    @Autowired
    ActiveDao activeDao;
    @Override
    public boolean add(Active a) {
        return activeDao.insert(a) >0 ? true : false;
    }

    @Override
    public boolean delete(Integer id) {
        return activeDao.deleteById(id)>0?true:false;
    }

    @Override
    public int deleteMore(List<Integer> ids) {
        return activeDao.deleteBatchIds(ids);
    }

    @Override
    public boolean update(Active active) {
        return activeDao.updateById(active)>0?true:false;
    }

    @Override
    public IPage<Active> getPageData(Integer pageNumber, Integer pageSize, String title, String startTime, String endTime) {
        System.out.println(pageNumber + "," + pageSize+ "," +title+ "," +startTime);
        QueryWrapper<Active> wrapper = new QueryWrapper<>() ;
        if(!StringUtil.isEmpty(title)) {
            wrapper.like(Active.COLUMN_TITLE ,title) ;
        }
        if(! StringUtil.isEmpty(startTime)&& !StringUtil.isEmpty(endTime)) {
            wrapper.between(Active.COLUMN_ADATETIME,startTime,endTime) ;
        }
        if(! StringUtil.isEmpty(startTime)&& StringUtil.isEmpty(endTime)) {
            wrapper.ge(Active.COLUMN_ADATETIME,startTime);
        }
        if(StringUtil.isEmpty(startTime)&& ! StringUtil.isEmpty(endTime)) {
            wrapper.le(Active.COLUMN_ADATETIME,endTime);
        }
        IPage<Active> page = new Page<>(pageNumber , pageSize) ;
        return activeDao.selectPage(page , wrapper) ;
    }
}
