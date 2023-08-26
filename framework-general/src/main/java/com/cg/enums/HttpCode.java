package com.cg.enums;

public enum HttpCode {
    //1、成功
    SUCCESS(200,"成功"),

    //2、警告

    //3、错误
    /**
     * 用户模块
     */
    PASSWORD_ERROR(4001,"用户名或者密码错误"),
    NOT_LOGIN(4002,"请登录后访问"),
    IDENTIFY_ERROR(4003,"用户认证失败"),
    NOT_POWER(4004,"权限不足，无法访问"),
    USER_EXIST(4006,"用户已经存在"),
    DELETE_ERROR(4007,"删除失败!"),
    TOKEN_ERROR(4008,"Token非法"),


    //未确定的系统其他错误
    SYSTEM_ERROR(500,"服务器错误，请等待或联系管理员");




    int code;
    String msg;

    HttpCode(int code,String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
