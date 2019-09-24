package com.isoft.youeryuan.dao;


import com.isoft.youeryuan.entity.Notice;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;
@Mapper
public interface NoticeDao {
    /**
     * 添加资讯
     */
    @Insert("insert into tb_notice(title,content) values(#{title},#{content})")
    int addNotice(String title,String content);

    /**
     * 删除资讯
     */
    @Delete("delete from tb_notice where id = #{id}")
    int deleteNotice(String id);
//    int deleteNotice(Integer id);

    /**
     * 修改资讯
     */
    @Update("update tb_notice set title = #{title},content = #{content} where id = #{id}")
    int updateNotice(Integer id,String title,String content);

    /**
     * 查找全部资讯
     */
    @Select("select * from tb_notice")
    List<Notice> selectAll();

    /**
     * 查找指定标题，在某范围内的资讯
     */
//    List<Notice> selectByTitleAndTime(String title,String startTime,String endTime);
    List<Notice> selectByTitleAndTime(HashMap map);

    int selectCount(String titleKey,String startTime,String endTime);


}
