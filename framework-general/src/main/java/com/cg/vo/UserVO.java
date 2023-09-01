package com.cg.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
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
    //邮箱
    private String email;

}
