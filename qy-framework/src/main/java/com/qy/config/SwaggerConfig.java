package com.qy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author: qy
 * @create: 2023/4/1 18:47
 * @description:
 **/
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qy.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("倩云个人博客系统", "http://www.qy.com", "qy@qq.com");
        return new ApiInfoBuilder()
                .title("个人博客系统")
                .description("博客系统接口")
                .contact(contact)   // 联系方式
                .version("1.1.0")  // 版本
                .build();
    }
}
