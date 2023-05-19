package com.qy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.qy.mapper")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableSwagger2
public class QyBlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(QyBlogAdminApplication.class, args);
    }
}