package com.cg.controller;

import com.cg.dto.UserDTO;
import com.cg.service.UserService;
import com.cg.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登陆
     * @param userDTO 用户信息传输对象，账户、密码
     * @return
     */
    @GetMapping("/login")
    public ResponseResult Login(@RequestBody UserDTO userDTO){
        return userService.Login(userDTO);
    }


}
