package com.isoft.youeryuan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("tb_student")
public class Student {
    public static final int STATE_LEAVE = 1;
    public static final int STATE_IN = 0;

    public static String COLUMN_ID = "id" ;
    public static String COLUMN_STATE = "state" ;
    public static String COLUMN_NAME = "sname" ;
    public static String COLUMN_CLASSID = "classid" ;
    public static String COLUMN_GENDER = "gender" ;
    public static String COLUMN_BIRTH = "birth" ;
    public static String COLUMN_ENTERDATE = "enterdate" ;

    @TableId(type = IdType.AUTO)
    private Integer id ;

    private String sid , sname ;
    @DateTimeFormat(pattern = "yyyy年MM月dd日")
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date birth ;
    private String gender , nation , photo , address , classid;
    @DateTimeFormat(pattern = "yyyy年MM月dd日")
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date enterdate ;
    private Integer state ;
    private String other ;

    @TableField(exist = false)
    private String classname ;

}
