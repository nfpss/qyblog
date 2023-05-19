package com.qy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: qy
 * @create: 2022/12/12 23:04
 * @description:
 **/
@SpringBootApplication
@MapperScan("com.qy.mapper")
@EnableScheduling
@EnableSwagger2
public class QyBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(QyBlogApplication.class, args);
    }
}
