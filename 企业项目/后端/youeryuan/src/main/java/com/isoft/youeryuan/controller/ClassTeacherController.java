package com.isoft.youeryuan.controller;

import com.isoft.youeryuan.bean.ResponseData;
import com.isoft.youeryuan.service.ClassTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classteacher")
@CrossOrigin
@Api("班级任课教师管理控制器")
public class ClassTeacherController {
    @Autowired
    ClassTeacherService classTeacherService ;

    @PostMapping("getall")
    @ApiOperation("获取所有分配教师的班级")
    public ResponseData getAll(){
        return new ResponseData(0  ,  "操作成功" , classTeacherService.getAll()) ;
    }
    @PostMapping("getClassNoTeacher")
    @ApiOperation("获取所有没有分配教师的班级")
    public ResponseData getClassNoTeacher() {
        return new ResponseData(0  ,  "操作成功" , classTeacherService.getClassNoTeacher()) ;
    }

    @PostMapping("getTeacherNoClass")
    @ApiOperation("获取所有没有分配班级的教师")
    public ResponseData getTeacherNoClass() {
        return new ResponseData(0  ,  "操作成功" , classTeacherService.getTeacherNoClass()) ;
    }

    @PostMapping("update")
    @ApiOperation("更新某个班级的授课教师")
    public ResponseData update(String cid , String tid) {
        boolean r = classTeacherService.updateTeacher(cid , tid) ;
        return new ResponseData(r ? 0 : 1  , r ? "更新成功" : "更新失败" , r) ;
    }

    @PostMapping("add")
    @ApiOperation("设置某个班级的授课教师")
    public ResponseData add(String cid , String tid) {
        boolean r = classTeacherService.add(cid , tid) ;
        return new ResponseData(r ? 0 : 1  , r ? "设置成功" : "设置失败" , r) ;
    }

    @PostMapping("del")
    @ApiOperation("删除所设置的班级授课信息")
    public ResponseData del(String cid) {
        boolean r = classTeacherService.del(cid) ;
        return new ResponseData(r ? 0 : 1  , r ? "删除成功" : "删除失败" , r) ;
    }
}
