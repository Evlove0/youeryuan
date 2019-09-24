package com.isoft.youeryuan.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.isoft.youeryuan.bean.Page;
import com.isoft.youeryuan.dao.NoticeDao;
import com.isoft.youeryuan.entity.Notice;
import com.isoft.youeryuan.service.NoticeService;
import com.isoft.youeryuan.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeDao noticeDao;

    @Override
    public boolean addNotice(String title, String content) {
        if (null==title||null==content||StringUtil.isEmpty(title)||StringUtil.isEmpty(content))
            return false;
        return noticeDao.addNotice(title,content)>0?true:false;
    }

/*
    @Override
    public boolean deleteNotice(Integer id) {
        if (null == id||id<1)
        return false;
        return noticeDao.deleteNotice(id)>0?true:false;
    }
*/
@Override
public boolean deleteNotice(String id) {
    if (null == id||StringUtil.isEmpty(id))
        return false;
    return noticeDao.deleteNotice(id)>0?true:false;
}

    @Override
    public boolean updateNotice(Integer id, String title, String content) {
        if (null == id||id<1||null==title||null==content||StringUtil.isEmpty(title)||StringUtil.isEmpty(content))
        return false;
        return noticeDao.updateNotice(id,title,content)>0?true:false;
    }

    @Override
    public List<Notice> selectAll() {
        return null;
    }

    @Override
    public Page<Notice> selectByTitleAndTime(Integer page , Integer pageSize , String title, String startTime, String endTime) {
        System.out.println(page + "," + pageSize+ "," + title+ "," + startTime+ "," + endTime);
        if(null == page || page < 0) {
            page = 1 ;
        }
        if(null == pageSize || pageSize < 1) {
            pageSize = 10 ;
        }
        if(StringUtil.isEmpty(title)) {
            title = null ;
        }
        if(StringUtil.isEmpty(startTime)) {
            startTime = null ;
        }
        if(StringUtil.isEmpty(endTime)) {
            endTime = null ;
        }
        HashMap map = new HashMap();
        map.put("titleKey",title);
        map.put("startTimt",startTime);
        map.put("endTime",endTime);
        map.put("offset",(page-1) * pageSize);
        map.put("rowCount",pageSize);

        List<Notice> data = noticeDao.selectByTitleAndTime(map) ;
        int count = noticeDao.selectCount(title,startTime,endTime) ;
        int pageCount = (int)(Math.ceil(count*1.0 / pageSize));
        return new Page<Notice>(data , page , pageSize , pageCount ,count) ;
    }

/*    @Override
    public int selectCount(String title, String startTIme, String endTime) {
        return 0;
    }*/


}
