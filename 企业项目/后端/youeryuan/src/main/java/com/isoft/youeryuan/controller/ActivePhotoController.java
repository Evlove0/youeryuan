package com.isoft.youeryuan.controller;

import com.isoft.youeryuan.bean.ResponseData;
import com.isoft.youeryuan.entity.ActivePhoto;
import com.isoft.youeryuan.service.ActivePhotoService;
import com.isoft.youeryuan.service.ActiveService;
import com.isoft.youeryuan.util.FileUtil;
import com.isoft.youeryuan.util.MyFileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("/activePhoto")
@CrossOrigin
@Api("图片管理控制器")

public class ActivePhotoController {
    @Autowired
    ActivePhotoService activePhotoService;

    public ResponseData addActivePhoto(ActivePhoto activePhoto){
        boolean result = activePhotoService.addPhoto(activePhoto);
        return new ResponseData(result?0:1,result?"添加图片成功":"添加图片失败",result);
    }
    public void myAddActivePhoto(String photo,int aid){
        System.out.println(photo+"myadd");
        activePhotoService.myAddActiePhoto(photo,aid);
    }

    // 头像上传
    private String photoPath="D:\\study\\企业项目\\前端\\Youeryuan\\assets\\activePhoto" ;   // 教职工头像保存路径
    private String addPhotoUri = null ;    // 先上传头像，然后再添加员工所有信息，保存至数据库
    private String updatePhotoUri = null ; // 更新先更新头像，然后再调用update更新更多信息，然后保存到数据库

    @PostMapping("uploadActivePhoto")
    @ApiOperation(("上传学生头像，其中id为null是新增学生时添加头像，不为null是已有学生更新头像"))
    public ResponseData upload(@ApiParam(name = "photo",value = "上传头像", required = true) @RequestParam("photo") MultipartFile file, Integer id) {
        System.out.println(file);
        System.out.println(id);
        boolean result = false ;
        String tempUri = MyFileUtil.uploadFile(file , id , photoPath) ;
        if(null != tempUri) {
            result = true ;
        }
        if(id == null) {
            addPhotoUri = tempUri ;
        } else {
            updatePhotoUri = tempUri ;
        }
//        ActivePhoto activePhoto = new ActivePhoto();
       /* activePhoto.setId(id);*/
/*        activePhoto.setPhoto(tempUri);
        System.out.println(activePhoto);
        addActivePhoto(activePhoto);*/
        System.out.println(tempUri+"insert");
        myAddActivePhoto(tempUri,id);
        return new ResponseData(result ? 0 : 1 , result ? "成功" : "失败" , tempUri) ;
    }


    @PostMapping("selectPhotoByAid")
    @ApiOperation("查看图片")
    public ResponseData selectPhotoByAid(Integer id){
        boolean result = false ;
        if (id>=1){
            result = true;
        }
        int count = activePhotoService.totalCount(id);
        List<ActivePhoto> activePhotos = activePhotoService.selectByAid(id);
        return new ResponseData(count , result ? "成功" : "失败" , activePhotos) ;
    }
    @PostMapping("selectPhotoCountByAid")
    @ApiOperation("查看图片数量")
    public Integer selectPhotoCountByAid(Integer id){
        boolean result = false ;
        if (id>=1){
            result = true;
        }
        int count= activePhotoService.totalCount(id);
        return count;
    }

}
