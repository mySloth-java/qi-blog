package com.cg.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2023-08-25 16:32:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //唯一标识符
    private String id;
    //账户
    private String account;
    //密码
    private String password;
    //用户昵称
    private String name;
    //用户头像地址
    private String img;
    //身份（0是普通用户，1是管理员）
    private String type;
    //邮箱
    private String email;
    //状态标记（0是被封，1是正常）
    private String status;
    //创建时间
    private Date createTime;
    //逻辑删除标记
    private String delFlag;


}

