package com.bdjbd.controller;

import com.bdjbd.Message;
import com.bdjbd.annotation.IgnoreAuthority;
import com.bdjbd.annotation.IgnoreUserToken;
import com.bdjbd.common.exception.auth.UserInvalidException;
import com.bdjbd.service.auth.AuthService;
import com.bdjbd.util.user.JwtAuthenticationRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.bdjbd.common.constant.RestCodeConstants.TOKEN_ERROR_CODE;

/**
 * @author dbc
 * @version 1.0
 * @className AuthController
 * @description 鉴权控制器
 * @date 2019/10/11
 **/
@RestController
@RequestMapping("/open/jwt")
@Slf4j
@Api(value = "登录模块 - Controller", description = "登录模块接口", tags = {"登录模块接口"})
public class AuthController {

    @Value("${jwt.token-header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    /**
     * 获取token
     *
     * @param authenticationRequest
     * @return
     */
    @ApiOperation(value = "获取token接口", notes = "获取token接口")
    @IgnoreUserToken
    @IgnoreAuthority
    @RequestMapping(value = "token", method = RequestMethod.POST)
    public Message<String> createAuthenticationToken(JwtAuthenticationRequest authenticationRequest, HttpServletRequest request) {
        try {
            log.info(authenticationRequest.getUsername() + "-" + authenticationRequest.getCode() + " require logging...");
            final String token = authService.login(authenticationRequest, request);
            if (authenticationRequest.getUsername().startsWith("10001"))
                return Message.success("专家", token);
            else
                return Message.success("获取成功", token);
        } catch (Exception e) {
            log.warn("获取token失败 {}", e.getMessage());
            if (e instanceof UserInvalidException) {
                return Message.error(TOKEN_ERROR_CODE, e.getMessage());
            }
            return Message.error(TOKEN_ERROR_CODE, "请稍后重试！");
        }
    }

    /**
     * 刷新token
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "刷新token接口", notes = "刷新token接口")
    @IgnoreAuthority
    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public Message<String> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        try {
            String token = request.getHeader(tokenHeader);
            String refreshedToken = authService.refresh(token);
            return Message.success("", refreshedToken);
        } catch (Exception e) {
            return Message.error(TOKEN_ERROR_CODE, "token 刷新错误，请重试！");
        }
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    @ApiOperation(value = "验证token接口", notes = "验证token接口")
    @IgnoreUserToken
    @IgnoreAuthority
    @RequestMapping(value = "verify", method = RequestMethod.GET)
    public Message<?> verify(String token) {
        try {
            authService.validate(token);
            return Message.success("验证成功！");
        } catch (Exception e) {
            return Message.error(TOKEN_ERROR_CODE, "验证失败！");
        }
    }
}
