package com.bdjbd.common.annotation;

import java.lang.annotation.*;

/**
  * @interfaceName Protocol
  * @description 方法注解（用于协议别名）
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Protocol {

    /**
     * 别名
     * @return String
     */
    String alias() default "";
}
