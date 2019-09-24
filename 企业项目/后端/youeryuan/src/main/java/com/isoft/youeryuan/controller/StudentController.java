package com.isoft.youeryuan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.isoft.youeryuan.bean.ResponseData;
import com.isoft.youeryuan.entity.Student;
import com.isoft.youeryuan.service.StudentService;
import com.isoft.youeryuan.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/student")
@CrossOrigin
@Api("学生管理控制器")
public class StudentController {
    @Autowired
    StudentService studentService ;

    @PostMapping("add")
    @ApiOperation("添加新学生")
    public ResponseData add(Student s) {
        s.setPhoto(addPhotoUri); addPhotoUri = null ;
        boolean result = studentService.add(s) ;
        return new ResponseData(result ? 0 : 1 , result ? "添加成功" : "添加失败!" , result) ;
    }

    @PostMapping("updateById")
    @ApiOperation("根据id更新学生信息")
    public ResponseData update(Student s) {
        s.setPhoto(updatePhotoUri); updatePhotoUri = null ;
        boolean result = studentService.update(s) ;
        return new ResponseData(result ? 0 : 1 , result ? "更新成功" : "更新失败!" , result) ;
    }

    @PostMapping("leaveById")
    @ApiOperation("某个学生离开")
    public ResponseData leaveById(Integer id) {
        boolean result = studentService.leave(id) ;
        return new ResponseData(result ? 0 : 1 , result ? "更新成功" : "更新失败!" , result) ;
    }

    @PostMapping("leave")
    @ApiOperation("多个学生离开")
    public ResponseData leave(Integer[] ids) {
        int result = studentService.leave(Arrays.asList(ids)) ;
        return new ResponseData(result > 0 ? 0 : 1 , result > 0 ? "更新了"+result+"个学生" : "更新失败!" , result) ;
    }

    @RequestMapping(value="pageData" , method = {RequestMethod.POST , RequestMethod.GET})
    @ApiOperation("分页搜索数据")
    public Map<String , Object> pageData(Integer pageNumber , Integer pageSize , String classid, String gender, String nameKey, String enterDate , Integer state){
        IPage<Student> page = studentService.getPageData(pageNumber , pageSize , classid , nameKey , gender , enterDate , state) ;

        Map<String , Object> map = new HashMap<>() ;
        map.put("rows" , page.getRecords()) ;
        map.put("total" , page.getTotal()) ;
        return map ;
    }



    // 头像上传
    @Value("${student.photo}")
    private String photoPath ;   // 教职工头像保存路径
    private String addPhotoUri = null ;    // 先上传头像，然后再添加员工所有信息，保存至数据库
    private String updatePhotoUri = null ; // 更新先更新头像，然后再调用update更新更多信息，然后保存到数据库

    @PostMapping("uploadPhoto")
    @ApiOperation(("上传学生头像，其中id为null是新增学生时添加头像，不为null是已有学生更新头像"))
    public ResponseData upload(@ApiParam(name = "photo",value = "上传头像", required = true) @RequestParam("photo") MultipartFile file, Integer id, HttpServletRequest request) {
        boolean result = false ;
        String tempUri = FileUtil.uploadFile(file , id , photoPath , request) ;
        if(null != tempUri) {
            result = true ;
        }
        if(id == null) {
            addPhotoUri = tempUri ;
        } else {
            updatePhotoUri = tempUri ;
        }
        return new ResponseData(result ? 0 : 1 , result ? "成功" : "失败" , tempUri) ;
    }

}
