package com.cg.util;

import com.cg.enums.HttpCode;
import com.cg.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //捕获自己抛出的异常
    @ExceptionHandler(GlobalException.class)
    public ResponseResult SystemExceptionHandler(GlobalException e){
        log.error("全局异常抛出!{}",e);

        int num = 2;
        System.out.println(2);

        return ResponseResult.errorResult(e.getCode(),e.getMsg());
    }

    //未考虑的其他异常捕获
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseResult OtherExceptionHandler(Exception e){

        return ResponseResult.errorResult(HttpCode.SYSTEM_ERROR,e.getMessage());
    }

}
