package com.cg.service;

import com.cg.dto.UserDTO;
import com.cg.vo.ResponseResult;

public interface UserService {

    ResponseResult Login(UserDTO userDTO);
}
