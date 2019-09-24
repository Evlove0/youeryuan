package com.isoft.youeryuan.controller;

import com.isoft.youeryuan.bean.ResponseData;
import com.isoft.youeryuan.entity.SysUser;
import com.isoft.youeryuan.service.SysUserService;
import com.isoft.youeryuan.util.FileUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/sysuser")
@Api("系统管理员控制器")
public class SysUserController {
    @Autowired
    private SysUserService sysUserServiceImpl;

    @RequestMapping("login")
    @ResponseBody
    @ApiOperation("系统管理员登录校验")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "登录账号", required = true),
            @ApiImplicitParam(name = "loginPass", value = "登录密码", required = true)
    })
    public ResponseData loginCheck(String loginName, String loginPass) {
        SysUser sysUser = sysUserServiceImpl.loginCheck(loginName, loginPass);
        int errCode = null == sysUser ? 1 : 0;
        String errMsg = null == sysUser ? "登录失败" : "登录成功";
        return new ResponseData(errCode, errMsg, sysUser);
    }

    @RequestMapping(value = "repass", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @ApiOperation("修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要修改密码管理员id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "oldPass", value = "原密码", required = true),
            @ApiImplicitParam(name = "newPass", value = "新密码", required = true),
    })
    public ResponseData repass(Integer id, String oldPass, String newPass) {
        int result = sysUserServiceImpl.repass(id, oldPass, newPass);
        String msg = "未知错误！" ;
        switch (result) {
            case SysUserService.REPASS_OK :
                msg = "修改成功!" ;
                break;
            case SysUserService.REPASS_INFONULL:
                msg = "信息不完整!" ;
                break;
            case SysUserService.REPASS_OLDPASSERROR:
                msg = "原密码错误!" ;
                break;
            case SysUserService.REPASS_OTHERERROR:
                msg = "其他错误!" ;
                break;
        }
        return new ResponseData(result , msg , null) ;
    }

    @GetMapping("getAll")
    @ResponseBody
    @ApiOperation("获取所有管理员信息")
    public List<SysUser> getAll()
    {
        return sysUserServiceImpl.getAll();
    }

    @PostMapping("add")
    @ResponseBody
    @ApiOperation("新增管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "账号", required = true),
            @ApiImplicitParam(name = "pass", value = "密码", required = true),
            @ApiImplicitParam(name = "role", value = "角色", required = true),
    })
    public ResponseData add(String name, String pass, String role) {
        boolean result = sysUserServiceImpl.add(name, pass, role);
        return new ResponseData(result ? 0 : 1, result ? "成功" : "失败", result);
    }

    @PostMapping("del")
    @ResponseBody
    @ApiOperation("删除管理员")
    @ApiImplicitParam(name = "id", value = "要删除管理员id", type = "int", required = true)
    public ResponseData del(Integer id) {
        boolean result = sysUserServiceImpl.del(id);
        return new ResponseData(result ? 0 : 1, result ? "成功" : "失败", result);
    }

    /**
     * 管理员上传头像
     */
    @Value("${sys.user.photo}")
    private String uploadPath ;

    @RequestMapping(value = "uploadPhoto" , method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("管理员上传头像")
    @ApiImplicitParam(name="id" , value = "用户id",required = true , dataType = "int")
    public ResponseData uploadPhoto(@ApiParam(name = "photo",value = "上传头像", required = true) @RequestParam("photo") MultipartFile file, Integer id, HttpServletRequest request) {
        boolean result = false ;
        String photoUri = FileUtil.uploadFile(file , id , uploadPath , request);   // 文件保存位置uri
        if(null != photoUri) {
            // 修改数据库--数据库保存文件名
            result = sysUserServiceImpl.rephoto(id, photoUri);
            result = true ;
        }
        // 方法返回值设定
        return new ResponseData(result ? 0 : 1 , result ? "成功" : "失败" , photoUri) ;
    }

}
















