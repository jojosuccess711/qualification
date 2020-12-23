package com.bdjbd.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
  * @className Swagger2Configuration
  * @description Swagger 配置
  * @author dbc
  * @date 2019/7/9
  * @version 1.0
  **/
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Configuration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bdjbd"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("业务接口文档")
                .description("此文档接口为内部使用，针对移动端小伙伴开发！")
                .contact(new Contact("DBC", "", "1070568622@qq.com"))
                .version("版本号：1.0")
                .build();
    }
}
