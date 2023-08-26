package com.cg.util;


import com.cg.enums.HttpCode;

public class GlobalException extends RuntimeException{

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public GlobalException(HttpCode httpCode) {
        super((httpCode.getMsg()));
        this.code = httpCode.getCode();
        this.msg = httpCode.getMsg();
    }
}
