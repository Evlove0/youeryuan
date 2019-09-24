package com.isoft.youeryuan.controller;

import com.isoft.youeryuan.bean.ResponseData;
import com.isoft.youeryuan.entity.Role;
import com.isoft.youeryuan.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("角色管理控制器")
@Controller
@CrossOrigin
@RequestMapping("role")
public class RoleController {
    @Autowired
    RoleService roleServiceImpl ;

    @GetMapping("getall")
    @ResponseBody
    @ApiOperation("获取所有角色数据")
    public ResponseData getAll() {
        return new ResponseData(0 , "成功" , roleServiceImpl.getAll());
    }

    @PostMapping("add")
    @ResponseBody
    @ApiOperation("添加角色")
    @ApiImplicitParam(name = "role" , value = "角色名称" , required = true)
    public ResponseData add(String role) {
        return new ResponseData(0 , "成功" , roleServiceImpl.add(role)) ;
    }
}
