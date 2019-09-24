package com.isoft.youeryuan.controller;

import com.isoft.youeryuan.bean.ResponseData;
import com.isoft.youeryuan.entity.ClassInfo;
import com.isoft.youeryuan.service.ClassInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("class")
@Api("班级管理控制器")
@CrossOrigin
public class ClassInfoController {
    @Autowired
    ClassInfoService classInfoService ;

    @PostMapping("add")
    @ApiOperation("添加一个班级")
    @ResponseBody
    public ResponseData add(ClassInfo classInfo) {
        boolean result = classInfoService.add(classInfo) ;
        return new ResponseData(result ? 0 : 1 , result ? "添加成功" : "添加失败" , result) ;
    }

    @PostMapping("delById")
    @ApiOperation("删除一个班级")
    @ResponseBody
    public ResponseData delById(Integer id) {
        boolean result = classInfoService.delById(id) ;
        return new ResponseData(result ? 0 : 1 , result ? "删除成功" : "删除失败" , result) ;
    }

    @GetMapping("getAll")
    @ApiOperation("获取所有班级信息")
    @ResponseBody
    public ResponseData getAll() {
        return new ResponseData(0 , "成功" , classInfoService.getAll()) ;
    }
}
