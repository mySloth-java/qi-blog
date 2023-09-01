package com.cg.mapper;

import com.cg.dto.UserDTO;
import com.cg.entity.User;
import com.cg.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    //security登陆验证
    User Login(@Param("name") String name);

    //注册用户
    Integer Register(UserDTO userDTO);

    //根据用户id查询具体信息
    User GetUserById(@Param("id") String id);


}
