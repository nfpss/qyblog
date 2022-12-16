package com.qy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qy.mapper")
public class QyBlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(QyBlogAdminApplication.class, args);
    }
}