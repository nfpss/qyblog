package com.qy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: qy
 * @create: 2022/12/12 23:04
 * @description:
 **/
@SpringBootApplication
@MapperScan("com.qy.mapper")
public class QyBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(QyBlogApplication.class, args);
    }
}
