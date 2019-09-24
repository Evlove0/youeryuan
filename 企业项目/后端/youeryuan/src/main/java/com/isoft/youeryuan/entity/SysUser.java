package com.isoft.youeryuan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class SysUser {
    private Integer id ;
    private String name ;
    @JsonIgnore
    private String pass ;
    private String role ;

    @DateTimeFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss" )
    private Date regDate ;
    private String photourl;

    private Integer state ;


}
