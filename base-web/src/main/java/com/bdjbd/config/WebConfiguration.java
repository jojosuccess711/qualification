package com.bdjbd.config;

import com.bdjbd.common.handler.GlobalExceptionHandler;
import com.bdjbd.interceptor.AuthorityInterceptor;
import com.bdjbd.interceptor.UserAuthRestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 */
@Configuration("adminWebConfig")
@Primary
public class WebConfiguration implements WebMvcConfigurer {
    @Bean
    GlobalExceptionHandler getGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserAuthRestInterceptor()).
                addPathPatterns(getIncludePathPatterns()).excludePathPatterns(getExcludePathPatterns());

        registry.addInterceptor(getAuthorityInterceptor()).
                addPathPatterns(getIncludePathPatterns()).excludePathPatterns();
    }

    @Bean
    UserAuthRestInterceptor getUserAuthRestInterceptor() {
        return new UserAuthRestInterceptor();
    }

    @Bean
    AuthorityInterceptor getAuthorityInterceptor(){
        return new AuthorityInterceptor();
    }


    private ArrayList<String> getExcludePathPatterns(){
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/open/**",
        };
        Collections.addAll(list, urls);
        return list;
    }


    /**
     * 需要用户和服务认证判断的路径
     * @return
     */
    private ArrayList<String> getIncludePathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/admin/**"
        };
        Collections.addAll(list, urls);
        return list;
    }

}
