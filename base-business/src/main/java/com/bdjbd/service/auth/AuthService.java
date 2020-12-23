package com.bdjbd.service.auth;

import com.bdjbd.common.util.jwt.IJWTInfo;
import com.bdjbd.util.user.JwtAuthenticationRequest;

import javax.servlet.http.HttpServletRequest;

/**
  * @interfaceName AuthService
  * @description 用户鉴权服务
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public interface AuthService {

    /**
     * 用户登录
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    String login(JwtAuthenticationRequest authenticationRequest, HttpServletRequest request) throws Exception;

    /**
     * 用户刷新token
     * @param oldToken
     * @return
     * @throws Exception
     */
    String refresh(String oldToken) throws Exception;

    /**
     * 用户验证token
     * @param token
     * @throws Exception
     */
    IJWTInfo validate(String token) throws Exception;
}
