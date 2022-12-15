package com.qy.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/15 16:13
 **/
@Component
@ConfigurationProperties(prefix = "oss")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OssConfig {

    private String ak;

    private String sk;

    private String bucket;

    private String responseUrl;
}
