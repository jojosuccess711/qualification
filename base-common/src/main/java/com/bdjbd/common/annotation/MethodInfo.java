package com.bdjbd.common.annotation;

import java.lang.annotation.*;

/**
  * @interfaceName MethodInfo
  * @description 方法注解（用于通用别名）
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo {

    /**
     * 别名
     * @return String
     */
    String alias();
}
