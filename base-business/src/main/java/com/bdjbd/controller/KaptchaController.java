package com.bdjbd.controller;

import com.bdjbd.annotation.IgnoreAuthority;
import com.bdjbd.annotation.IgnoreUserToken;
import com.bdjbd.service.common.KaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: dbc
 * @Date: 2018/9/7
 * @Version: 1.0
 * @Description:
 */
@Api(value = "图形验证码接口 - Controller", description = "图形验证码接口", tags = {"图形验证码接口"})
@Slf4j
@Controller
@RequestMapping("/open/kaptcha")
public class KaptchaController {

    @Autowired
    private KaptchaService kaptchaService;

    /**
     * 获取图片验证码
     * @param request
     * @param response
     */
    @ApiOperation(value = "获取验证码接口", notes = "获取验证码")
    @IgnoreUserToken
    @IgnoreAuthority
    @GetMapping("/")
    public void getKaptcha(HttpServletRequest request, HttpServletResponse response){
        try{
            kaptchaService.getCaptcha(request.getSession().getId(), request, response);
        }catch (Exception e){
            log.error("获取验证码异常", e);
        }
    }
}
