package com.isoft.youeryuan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
public class Employee {
    public static final int ROLE_TEACHER = 0 ;
    public static final int ROLE_EMPLOYEE = 1 ;
    public static final int STATE_HIREDATE = 0 ;
    public static final int STATE_DEPARTURE = 1 ;
    public static final int STATE_TRY = 2 ;

    private Integer id ;
    private String tid , tname ;
    @JsonIgnore
    private String pass;
    private Integer role ;
    private String nation ;
    private String education , school , gender , email;
    private String mobile , address , photo;
    private  String birth ,hiredate,departure;

/*    @DateTimeFormat(pattern = "yyyy年MM月dd日")
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date birth ;
    @DateTimeFormat(pattern = "yyyy年MM月dd日")
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date hiredate ;
    @DateTimeFormat(pattern = "yyyy年MM月dd日")
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date departure ;*/
    private Integer state ;

    /**
     * 没有id，tid，departure=null，state=2 试用
     */
    public Employee(String tname, Integer role, String nation, String education, String school, String gender, String email, String birth, String mobile, String address, String photo , String hiredate) {
        this.tname = tname;
        this.role = role;
        this.nation = nation;
        this.education = education;
        this.school = school;
        this.gender = gender;
        this.email = email;
        this.birth = birth;
        this.mobile = mobile;
        this.address = address;
        this.photo = photo;
        this.hiredate = hiredate;
    }

    public Employee(){}

    public Employee(Integer id , String tid , String tname) {
        this.id = id ;
        this.tid = tid ;
        this.tname = tname ;
    }

}
