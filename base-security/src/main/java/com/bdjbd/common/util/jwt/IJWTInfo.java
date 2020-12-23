package com.bdjbd.common.util.jwt;

/**
  * @interfaceName IJWTInfo
  * @description JWT信息接口定义
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public interface IJWTInfo {

    /**
     * 获取用户名
     * @return
     */
    String getUniqueName();

    /**
     * 获取用户ID
     * @return
     */
    String getId();

    /**
     * 获取名称
     * @return
     */
    String getName();
}
