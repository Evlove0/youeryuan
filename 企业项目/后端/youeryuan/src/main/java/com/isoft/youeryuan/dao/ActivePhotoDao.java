package com.isoft.youeryuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoft.youeryuan.entity.ActivePhoto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActivePhotoDao extends BaseMapper {
    @Insert("insert into tb_activephoto(photo,aid) values(#{photo},#{aid})")
    boolean myAddActiePhoto(String photo,int aid);

    @Select("select * from tb_activePhoto where aid = #{id}")
    List<ActivePhoto> selectPhotoByAid(Integer id);

    @Select("select count(*) from tb_activePhoto where aid = #{id}")
    int totalCount(Integer id);
}
