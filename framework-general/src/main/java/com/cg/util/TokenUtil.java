package com.cg.util;


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;

import java.util.HashMap;

import static com.cg.util.SystemConstants.EXPIRE_DAY;

//使用HuTOOl封装自己的token生成
public class TokenUtil {
    //TODO 密码学加密
    static String key = "asd";


    /**
     * 生成Token
     * @param id 用户Id
     * @return
     */
    public static String CreateToken(String id){
        HashMap<String, Object> map = new HashMap<>();

        DateTime now = DateTime.now();
        DateTime expireTime = now.offsetNew(DateField.DAY_OF_MONTH, EXPIRE_DAY);
        //签发时间
        map.put(JWTPayload.ISSUED_AT, now);
        //过期时间
        map.put(JWTPayload.EXPIRES_AT,expireTime);
        //生效时间
        map.put(JWTPayload.NOT_BEFORE,now);
        //载荷
        map.put("id",id);

        String token = JWTUtil.createToken(map, key.getBytes());
        return token;
    }
}
