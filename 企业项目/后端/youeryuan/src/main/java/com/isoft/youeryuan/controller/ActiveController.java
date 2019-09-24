package com.isoft.youeryuan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.isoft.youeryuan.bean.ResponseData;
import com.isoft.youeryuan.entity.Active;
import com.isoft.youeryuan.service.ActiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/active")
@CrossOrigin
@Api("活动管理控制器")

public class ActiveController {
    @Autowired
    ActiveService activeService;

    @PostMapping("addActive")
    @ApiOperation("添加活动")
    public ResponseData addActive(Active a){
        boolean result = activeService.add(a);
        return new ResponseData(result?0:1,result?"添加活动成功":"添加活动失败",result);
    }
    @PostMapping("deleteActive")
    @ApiOperation("删除活动")
    public ResponseData deleteMoreActive(Integer[] ids){
        int result = activeService.deleteMore(Arrays.asList(ids));
        return new ResponseData(result>0?0:1,result>0?"删除活动成功":"删除活动失败",result);
    }
    @PostMapping("updateActive")
    @ApiOperation("更新活动")
    public ResponseData updateActive(Active a){
        boolean result =activeService.update(a);
        return new ResponseData(result?0:1,result?"更新活动成功":"更新活动失败",result);
    }
    @RequestMapping(value="selectActive" , method = {RequestMethod.POST , RequestMethod.GET})
    @ApiOperation("查看活动")
    public Map<String , Object> pageData(Integer pageNumber , Integer pageSize , String title, String startTime, String endTime){
        System.out.println(pageNumber+pageSize+title+startTime+endTime);
        IPage<Active> page = activeService.getPageData(pageNumber , pageSize , title , startTime , endTime ) ;
        Map<String , Object> map = new HashMap<>() ;
        map.put("rows" , page.getRecords()) ;
        map.put("total" , page.getTotal()) ;
        return map ;
    }
}
