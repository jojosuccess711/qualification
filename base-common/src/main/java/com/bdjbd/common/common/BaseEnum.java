package com.bdjbd.common.common;

/**
  * @interfaceName BaseEnum
  * @description 自定义枚举基类
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public interface BaseEnum<T> {

    /**
     * key
     * @return T
     */
    T getKey();
}
