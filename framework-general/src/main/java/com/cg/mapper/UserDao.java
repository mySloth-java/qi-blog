package com.cg.mapper;

import com.cg.dto.UserDTO;
import com.cg.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    //security登陆验证
    User Login(String username);

    //注册用户
    Integer Register(UserDTO userDTO);


}
