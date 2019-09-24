package com.isoft.youeryuan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@TableName("tb_class")
public class ClassInfo {
    @TableId(type = IdType.AUTO)
    private Integer id ;
    @NotBlank(message = "班级cid不允许为空")
    private String cid ;
    @NotBlank(message = "班级名称不能为空")
    private String cname ;
}
