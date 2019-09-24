package com.isoft.youeryuan.controller.advice;

import com.isoft.youeryuan.bean.ResponseData;
import com.isoft.youeryuan.bean.TestException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

/**
 * 异常处理控制切面类
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = TestException.class) 		//@ExceptionHandler 该注解声明所捕获的异常
    @ResponseBody
    public ResponseData defaultErrorHandler(HttpServletRequest req, TestException e) throws Exception {
        return new ResponseData(e.getCode() , e.getMessage() , req.getRequestURI()) ;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        return mav;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request,  ConstraintViolationException e) {
        ModelAndView mav = new ModelAndView("errorValid");
        mav.addObject("invalid", e.getConstraintViolations());
        mav.addObject("url", request.getRequestURL());
        return mav;
    }
}
