/**
 * @filename:SysAdminController 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.controller;

import com.bdjbd.Message;
import com.bdjbd.annotation.IgnoreAuthority;
import com.bdjbd.annotation.IgnoreUserToken;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.bdjbd.common.CommonConstants.EX_AUTHORITY_CODE;
import static com.bdjbd.common.CommonConstants.EX_TOKEN_ERROR_CODE;

/**   
 * 
 * @Description:  通用接口层
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Slf4j
@RestController
@RequestMapping("/error")
@Api(value = "通用接口 - Controller", description = "通用接口", tags = {"通用接口"})
public class ErrorController {

	@RequestMapping(value = "/token", method = {RequestMethod.GET, RequestMethod.POST})
	@IgnoreAuthority
	@IgnoreUserToken
	public Message<?> token(){
		return Message.error(EX_TOKEN_ERROR_CODE, "token错误");
	}

	@RequestMapping(value = "/authority", method = {RequestMethod.GET, RequestMethod.POST})
	@IgnoreAuthority
	@IgnoreUserToken
	public Message<?> authority(){
		return Message.error(EX_AUTHORITY_CODE, "权限错误");
	}
}