package com.bdjbd.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
  * @className KeyConfiguration
  * @description 安全key配置
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
@Configuration
@PropertySource("classpath:config/data.properties")
@Data
public class KeyConfiguration {

    @Value("${jwt.rsa-secret}")
    private String userSecret;
    private byte[] userPubKey;
    private byte[] userPriKey;
}
