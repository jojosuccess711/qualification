package com.bdjbd.service.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: dbc
 * @Date: 2018/9/7
 * @Version: 1.0
 * @Description:
 */
public interface KaptchaService {

    /**
     * 获取验证码
     * @param randomStr 随机码
     * @param request
     * @param response
     */
    void getCaptcha(String randomStr, HttpServletRequest request, HttpServletResponse response);


}
