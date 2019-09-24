package com.isoft.youeryuan.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.isoft.youeryuan.entity.Active;


import java.util.List;

public interface ActiveService {
    boolean add(Active s) ;

    boolean delete(Integer id) ;

    int deleteMore(List<Integer> ids) ;

    boolean update(Active active) ;

//    Active getById(Integer id) ;

    IPage<Active> getPageData(Integer pageNumber, Integer pageSize,String titleKey, String startTime, String endTime) ;


}
