package com.isoft.youeryuan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_activephoto")
public class ActivePhoto {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer aid;
    private  String photo;
}
