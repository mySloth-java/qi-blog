package com.cg.mapper;

import com.cg.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {


    User Login(String username);
}
