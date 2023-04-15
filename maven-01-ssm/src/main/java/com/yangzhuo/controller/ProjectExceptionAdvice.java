package com.yangzhuo.controller;

import com.yangzhuo.exception.BusinessException;
import com.yangzhuo.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//声明这是一个异常处理类
public class ProjectExceptionAdvice {

    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException ex){
        //记录日志
        //发送消息给运维
        //发送邮件给开发人员,ex对象发送给开发人员
        return new Result(ex.getCode(), null,ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException ex){

        return new Result(ex.getCode(), null,ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex){
        //System.out.println("拦截到异常");
        return new Result(Code.SYSTEM_UNKNOW_ERR,null,"系统繁忙，请稍候再试");
    }
}
