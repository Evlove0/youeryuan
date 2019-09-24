package com.isoft.youeryuan.controller;

import com.isoft.youeryuan.bean.Page;
import com.isoft.youeryuan.bean.ResponseData;
import com.isoft.youeryuan.entity.Notice;

import com.isoft.youeryuan.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/notice")
@Api("资讯管理控制器")
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    @ApiOperation("添加资讯")
//    @PostMapping("addNotice")
    @RequestMapping(value="addNotice" , method = {RequestMethod.POST , RequestMethod.GET})
    public ResponseData addNotice(String title,String content){
        System.out.println(title+content+"=control");
        boolean result = noticeService.addNotice(title,content);
        return new ResponseData(result ? 0 : 1 , result ? "添加资讯成功" : "添加资讯失败!" , result) ;
    }

    @PostMapping("deleteNotice")
    @ApiOperation("删除资讯")
    public ResponseData deleteNotic(String id){
        System.out.println("id="+id);
        int i = 0;
        boolean result=false;
        String[] arr = id.split(",");
        for (String str : arr) {
            result  = noticeService.deleteNotice(str);
        }
        /*boolean result  = noticeService.deleteNotice(id);
        ;*/
        return  new ResponseData(result?0:1,result?"删除成功":"删除失败",result);
    }
    @PostMapping("updateNotice")
    @ApiOperation("修改资讯")
    public ResponseData updateNotic(Integer id,String title,String content){
        boolean result  = noticeService.updateNotice(id,title,content);
        return  new ResponseData(result?0:1,result?"修改成功":"修改失败",result);
    }

    @RequestMapping(value="selectByTitleAndTime" , method = {RequestMethod.POST , RequestMethod.GET})
    @ApiOperation("分页搜索数据")
    public Map<String, Object> selectByTitleAndTime(@RequestParam("pageNumber")Integer page , Integer pageSize , String title, String startTime, String endTime){
        Page<Notice> pageInfo = noticeService.selectByTitleAndTime(page, pageSize, title, startTime, endTime);
        HashMap<String,Object> map = new HashMap();
        map.put("rows",pageInfo.getData());
        map.put("total",pageInfo.getCount());
        return map;
    }



}
