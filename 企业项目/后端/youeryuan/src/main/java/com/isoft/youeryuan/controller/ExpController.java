package com.isoft.youeryuan.controller;

import com.isoft.youeryuan.bean.ResponseData;
import com.isoft.youeryuan.bean.TestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Api("异常测试控制器")
public class ExpController {
    @RequestMapping(value = "test" , method = RequestMethod.GET)
    @ApiOperation("发生500错误")
    public ResponseData test() {
        return null ;
    }

    @RequestMapping(value = "testExp" , method = RequestMethod.GET)
    @ApiOperation("抛出TestException")
    public ResponseData test2() throws TestException {
        if(true) {
            throw new TestException("发生了自定义的TestException") ;
        }
        return null ;
    }

    @RequestMapping(value = "testExpAll" , method = RequestMethod.GET)
    @ApiOperation("抛出TestException")
    public ResponseData test3() throws Exception {
        if(true) {
            throw new Exception("发生了Exception") ;
        }
        return null ;
    }
}
