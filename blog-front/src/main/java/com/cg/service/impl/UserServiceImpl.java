package com.cg.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.cg.dto.UserDTO;
import com.cg.entity.User;
import com.cg.enums.HttpCode;
import com.cg.mapper.UserDao;
import com.cg.service.UserService;
import com.cg.util.GlobalException;
import com.cg.util.TokenUtil;
import com.cg.vo.LoginUser;
import com.cg.vo.ResponseResult;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.cg.util.SystemConstants.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



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
        //authenticate的结果为null则表示没有用户
        if(Objects.isNull(authenticate)){
            throw new GlobalException(HttpCode.LOGIN_ERROR);
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

        //6、以用户Id为key，用户信息为value存储redis中，并设置过期时间
        String info = JSONUtil.toJsonPrettyStr(principal);
        stringRedisTemplate.opsForValue().set(USER_INFO+id,info,EXPIRE_DAY, TimeUnit.DAYS);

        return ResponseResult.okResult(userInfo);
    }

    /**
     * 退出
     * @return
     */
    @Override
    public ResponseResult Logout() {
        //1、当访问接口时，肯定会进入Token拦截器，根据token解析出用户Id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser principal = (LoginUser) authentication.getPrincipal();
        String id = principal.getUser().getId();

        //2、根据Id删除Redis缓存
        Boolean delete = stringRedisTemplate.opsForValue().getOperations().delete(USER_INFO + id);
        if(!delete){
            throw new GlobalException(HttpCode.DELETE_ERROR);
        }

        return ResponseResult.okResult(200,"删除成功!");
    }

    /**
     * 注册
     * TODO validation和ES搜索引擎
     * @param userDTO 用户传输对象
     * @return
     */
    @Override
    public ResponseResult Register(UserDTO userDTO) {
        //1、TODO 参数判空和格式校验

        //2、查询每个参数是否已经被注册过，邮箱、用户名、昵称


        //3、对密码进行加密，必须与解密一致，即security中的BCryptPasswordEncoder
        String ScreePw = bCryptPasswordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(ScreePw);
        userDTO.setId(IdUtil.simpleUUID());
        //TODO 名字长度简化
        userDTO.setName(USER_NAME+IdUtil.simpleUUID());
        userDTO.setCreateTime(DateTime.now());

        //4、注册
        Integer register = userDao.Register(userDTO);
        if(register == null){
            throw new GlobalException(HttpCode.REGISTER_ERROR);
        }

        //5、注册成功添加关键信息到ES引擎中

        return ResponseResult.okResult(register);
    }

    /**
     * 重写security登陆拦截器查询数据库操作
     * TODO 权限表添加
     * @param username 上一个拦截器传过来的用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1、查询数据库是否存在，不存在抛出异常
        User login = userDao.Login(username);
        if(Objects.isNull(login)){
            throw new GlobalException(HttpCode.PASSWORD_ERROR);
        }

        //2、添加权限表信息

        //3、封装结果
        return new LoginUser(login);
    }
}
