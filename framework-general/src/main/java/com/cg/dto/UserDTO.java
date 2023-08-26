package com.cg.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
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
    //创建时间
    private Date createTime;



}
