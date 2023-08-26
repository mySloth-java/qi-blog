package com.cg;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class UserConTest {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 密码加密和解密测试
     */
    @Test
    public void PasswordTest(){
        String encode = bCryptPasswordEncoder.encode("123");
        System.out.println(encode);
        boolean matches = bCryptPasswordEncoder.matches("123", encode);
        System.out.println(matches);

    }

    /**
     * JWT时间测试
     */
    @Test
    public void JWTTimeTest(){
        DateTime now = DateTime.now();
        DateTime dateTime = now.offsetNew(DateField.DAY_OF_YEAR, 7);
        System.out.println("年:"+dateTime);
        DateTime dateTime2 = now.offsetNew(DateField.DAY_OF_MONTH, 7);
        System.out.println("月:"+dateTime2);

    }

}
