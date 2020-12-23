package com.bdjbd;

import com.bdjbd.configuration.AutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
  * @interfaceName EnableAceAuthClient
  * @description 允许客户端鉴权注解
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoConfiguration.class)
@Documented
@Inherited
public @interface EnableAceAuthClient {
}
