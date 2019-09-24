package com.isoft.youeryuan.service;

import com.isoft.youeryuan.bean.Page;
import com.isoft.youeryuan.entity.Notice;

import java.util.HashMap;
import java.util.List;

public interface NoticeService {
    boolean addNotice(String title, String content);

    boolean deleteNotice(String id);

//    boolean deleteNotice(Integer id);

    boolean updateNotice(Integer id, String title, String content);

    List<Notice> selectAll();

    Page<Notice> selectByTitleAndTime(Integer page , Integer pageSize ,String title, String startTime, String endTime);

//    int selectCount(String title,String startTIme,String endTime);
}
