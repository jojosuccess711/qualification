package com.bdjbd.configuration;

import com.bdjbd.config.UserAuthConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
  * @className AutoConfiguration
  * @description 自动加载配置
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
@Configuration
public class AutoConfiguration {

    @Bean
    UserAuthConfig getUserAuthConfig(){
        return new UserAuthConfig();
    }

}
