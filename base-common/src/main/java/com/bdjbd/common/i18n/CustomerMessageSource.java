package com.bdjbd.common.i18n;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
  * @className CustomerMessageSource
  * @description 自定义国际化信息源
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public class CustomerMessageSource extends ReloadableResourceBundleMessageSource {

    public CustomerMessageSource() {
        super.setCacheSeconds(3600);
        super.setDefaultEncoding("utf-8");
        super.setUseCodeAsDefaultMessage(true);
    }

    public void setBasenames(String... basenames) {
        super.setBasenames(basenames);
    }

    public void setCacheSeconds(int seconds){
        super.setCacheSeconds(seconds);
    }

    public void setDefaultEncoding(String defaultEncoding){
        super.setDefaultEncoding(defaultEncoding);
    }

    public void setUseCodeAsDefaultMessage(boolean useCodeAsDefaultMessage){
        super.setUseCodeAsDefaultMessage(useCodeAsDefaultMessage);
    }
}
