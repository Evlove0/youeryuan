package com.isoft.youeryuan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("tb_active")
public class Active {
    public static String COLUMN_ID="id";
    public static String COLUMN_TITLE="title";
    public static String COLUMN_ADATETIME="adatetime";
    public static String COLUMN_CONTENT="content";
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
//    @DateTimeFormat(pattern = "yyyy年MM月dd日")
//    @JsonFormat(pattern = "yyyy年MM月dd日")
    private String adatetime;
    private  String content;
}
