package com.isoft.youeryuan.controller;

import com.isoft.youeryuan.bean.Page;
import com.isoft.youeryuan.bean.ResponseData;
import com.isoft.youeryuan.entity.Employee;
import com.isoft.youeryuan.service.EmployeeService;
import com.isoft.youeryuan.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/employee")
@Api("教职工管理控制器")
public class EmployeeController {
    @Autowired
    EmployeeService employeeServiceImpl ;


    @RequestMapping(value = "add" , method = {RequestMethod.POST , RequestMethod.GET})
    @ApiOperation(("添加一个新员工"))
    public ResponseData add(
                            String tname , String nation , String education , String school ,
                            String gender , String email ,
                            @DateTimeFormat(pattern = "yyyy年MM月dd日") String birth ,
                            String mobile  , String address , Integer role ,
                            @DateTimeFormat(pattern = "yyyy年MM月dd日") String hiredate
                            ) {
        Employee employee = new Employee(tname ,  role , nation , education , school , gender , email , birth , mobile , address , addPhotoUri , hiredate) ;
        addPhotoUri = null ;

        boolean result = employeeServiceImpl.add(employee) ;
        return new ResponseData(result ? 0 : 1 , result ? "添加成功" : "添加失败!" , result) ;
    }

    @PostMapping("leave")
    @ApiOperation("修改员工状态为离职")
    @ApiImplicitParam(name = "id" , value = "员工id" , required = true , dataType = "int")
    public ResponseData leave(Integer id) {
        boolean result = employeeServiceImpl.departure(id) ;
        return new ResponseData(result ? 0 : 1 , result ? "修改状态为离职成功" : "修改状态为离职失败!" , result) ;
    }

    @PostMapping("hiredate")
    @ApiOperation("修改员工状态为正式员工")
    @ApiImplicitParam(name = "id" , value = "员工id" , required = true , dataType = "int")
    public ResponseData hiredate(Integer id) {
        boolean result = employeeServiceImpl.changeState(id) ;
        return new ResponseData(result ? 0 : 1 , result ? "修改状态为正式员工成功" : "修改状态为正式员工失败!" , result) ;
    }

    @PostMapping("update")
    @ApiOperation("修改员工信息")
    public ResponseData update(Integer id, String email, String mobile, String address, String photo) {
        photo = updatePhotoUri ; updatePhotoUri = null ;

        boolean result = employeeServiceImpl.update(id , email , mobile , address , photo) ;
        return new ResponseData(result ? 0 : 1 , result ? "更新成功" : "更新失败!" , result) ;
    }

    @PostMapping("getByTid")
    @ApiOperation("根据员工编号获取员工个人详细信息")
    public ResponseData getById(String tid) {
        Employee employee = employeeServiceImpl.getById(tid) ;
        return new ResponseData(null != employee ? 0 : 1 , null != employee ? "获取信息成功" : "获取信息失败" , employee) ;
    }

    @RequestMapping(value="pageData" , method = {RequestMethod.POST , RequestMethod.GET})
    @ApiOperation("分页搜索数据")
    public Map<String , Object> pageData(@RequestParam("pageNumber") Integer page , Integer pageSize , Integer role, String gender, String nameKey, String mobileKey, Integer state){
        Page<Employee> pageInfo = employeeServiceImpl.search(page , pageSize , role , gender , nameKey , mobileKey , state) ;
        Map<String , Object> map = new HashMap<>() ;
        map.put("rows" , pageInfo.getData()) ;
        map.put("total" , pageInfo.getCount()) ;
        return map ;
    }

    @GetMapping("getAllTeacher")
    @ApiOperation("获取所有教师id，编号，姓名")
    public ResponseData getAllTeacher() {
        List<Employee> list = employeeServiceImpl.getAllTeacher() ;
        return new ResponseData(0 , "获取信息成功" , list) ;
    }

        // 头像上传
        @Value("${emp.photo}")
        private String photoPath ;   // 教职工头像保存路径
        private String addPhotoUri = null ;    // 先上传头像，然后再添加员工所有信息，保存至数据库
        private String updatePhotoUri = null ; // 更新先更新头像，然后再调用update更新更多信息，然后保存到数据库

        @PostMapping("uploadPhoto")
        @ApiOperation(("上传员工头像，其中id为null是新增员工时添加头像，不为null是已有员工更新头像"))
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
















