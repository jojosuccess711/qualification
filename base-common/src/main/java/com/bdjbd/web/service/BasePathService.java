package com.bdjbd.web.service;

import javax.servlet.http.HttpServletRequest;

/**
  * @interfaceName BasePathService
  * @description Service上下文路径
  * @author dbc
  * @date 2019/7/10
  * @version 1.0
  **/
public interface BasePathService {

    /**
     * 获取basePath
     * @param request
     * @return
     */
    String basePath(HttpServletRequest request);

    /**
     * 设置上下文
     * @param contextPath
     */
    void setContextPath(String contextPath);

    /**
     * 获取上下文
     * @return
     */
    String getContextPath();
}
