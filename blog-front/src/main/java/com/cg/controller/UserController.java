package com.cg.controller;

import com.cg.dto.UserDTO;
import com.cg.service.UserService;
import com.cg.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 退出
     * @return
     */
    @PostMapping("/logout")
    public ResponseResult Logout(){
        return userService.Logout();
    }

    /**
     * 注册
     * @param userDTO 用户传输对象
     * @return
     */
    @PostMapping("/register")
    public ResponseResult Register(@RequestBody UserDTO userDTO){
        return userService.Register(userDTO);
    }


}
