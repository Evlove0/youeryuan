package com.isoft.youeryuan;

import com.isoft.youeryuan.dao.NoticeDao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.isoft.youeryuan.dao.EmployeeDao;
import com.isoft.youeryuan.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.isoft.youeryuan.dao")
public class NoticeTest {
    @Autowired
    NoticeDao noticeDao;
    @Test
    public void testAdd(){
        System.out.println(noticeDao.addNotice("test1", "Content_test1")>0?"添加成功":"添加失败");
    }
/*    @Test
    public  void testDelete(){
        System.out.println(noticeDao.deleteNotice('8')>0?"删除成功":"删除失败");
    }
    @Test*/
    public void testUpdate(){
        System.out.println(noticeDao.updateNotice(5,"testUpdate","testUpdate Content")>0?"修改成功":"修改失败");
    }
    @Test
    public void testSelectAll(){
        System.out.println(noticeDao.selectAll());
    }
    @Test
    public void testSelectByIdAndTime(){
        HashMap map = new HashMap();
        map.put("titleKey","第");
        map.put("startTime","2019-09-17 19:11:11");
        map.put("endTime","2019-09-18 17:16:00");
//        System.out.println(noticeDao.selectByTitleAndTime("第","2019-09-17 17:16:07","2019-09-19 17:16:07"));
        map.put("offset","0");
        map.put("rowCount","2");
        System.out.println(noticeDao.selectByTitleAndTime(map));

    }
    @Test
    public void testSelectCount(){
        System.out.println(noticeDao.selectCount("第","2019-09-17 19:11:11","2019-09-19 19:11:11"));
    }



}
