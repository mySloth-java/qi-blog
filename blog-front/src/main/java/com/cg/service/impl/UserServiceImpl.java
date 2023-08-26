package com.cg.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.cg.dto.UserDTO;
import com.cg.entity.User;
import com.cg.mapper.UserDao;
import com.cg.service.UserService;
import com.cg.util.TokenUtil;
import com.cg.vo.LoginUser;
import com.cg.vo.ResponseResult;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.cg.util.SystemConstants.EXPIRE_DAY;
import static com.cg.util.SystemConstants.USER_TOKEN;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AuthenticationManager authenticationManager;



    /**
     * 登陆
     * @param userDTO 用户传输对象
     * @return
     */
    @Override
    public ResponseResult Login(UserDTO userDTO) {
        //1、注入authenticationManager进行用户认证，将账号和密码封装成authentication
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDTO.getAccount(),userDTO.getPassword());
        Authentication authenticate = authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

        //2、此时密码校验完，Authentication结果封装给providerManager过滤器完成校验操作，调用UserDetails实现类方法
        //authenticate的结果为null则表示没有用户 TODO
        if(Objects.isNull(authenticate)){
            throw  new RuntimeException("登陆失败!");
        }

        //3、校验完的结果为之前返回的LoginUser,强转取出用户Id JWT加密
        LoginUser principal = (LoginUser) authenticate.getPrincipal();
        String id = principal.getUser().getId().toString();

        //4、生成JWT以及配置规则（过期时间）
        String token = TokenUtil.CreateToken(id);

        //5、封装用户信息和Token返回前端
        HashMap<String, Object> userInfo = new HashMap<>();
        userInfo.put("info",principal);
        userInfo.put("token",token);

        //6、以用户Id为key，token为value存储redis中，并设置过期时间
        stringRedisTemplate.opsForValue().set(USER_TOKEN+id,token,EXPIRE_DAY, TimeUnit.DAYS);

        return ResponseResult.okResult(userInfo);
    }

    /**
     * 重写security登陆拦截器查询数据库操作
     * @param username 上一个拦截器传过来的用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1、查询数据库是否存在，不存在抛出异常
        User login = userDao.Login(username);
        //TODO 改成自定义全局异常
        if(Objects.isNull(login)){
            throw new RuntimeException("密码错误");
        }

        //2、添加权限表信息

        //3、封装结果
        return new LoginUser(login);
    }
}
