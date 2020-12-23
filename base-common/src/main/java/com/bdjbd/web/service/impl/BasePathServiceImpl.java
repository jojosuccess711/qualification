package com.bdjbd.web.service.impl;

import com.bdjbd.web.service.BasePathService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
  * @className BasePathServiceImpl
  * @description ServiceImpl上下文路径
  * @author dbc
  * @date 2019/7/10
  * @version 1.0
  **/
@Slf4j
@Service
public class BasePathServiceImpl implements BasePathService {

    private String contextPath;

    /**
     * 获取basePath
     *
     * @param request
     * @return
     */
    @Override
    public String basePath(HttpServletRequest request) {
        String path = request.getContextPath();
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int port = request.getServerPort();
        if(port == 80 || port == 443)
            path = getContextPath() == null ? "" : getContextPath();
        String basePath = scheme + "://" + serverName + (port != 80 && port != 443 ? ":" + port : "") + path;
        request.setAttribute("basePath", basePath);
        log.info("basePath --> " + basePath);
        return basePath;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
}
