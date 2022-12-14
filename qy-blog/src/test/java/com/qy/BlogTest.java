package com.qy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: qy
 * @create: 2022/12/14 21:42
 * @description:
 **/
@SpringBootTest
public class BlogTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void tes(){
        System.out.println(passwordEncoder.encode("123456"));
    }

    @Test
    void tesasd(){

    }
}
