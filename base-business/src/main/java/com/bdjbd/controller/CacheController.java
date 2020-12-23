/**
 * @filename:SysAdminController 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.controller;

import com.bdjbd.Message;
import com.bdjbd.annotation.IgnoreAuthority;
import com.bdjbd.service.common.CacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * 
 * @Description:  管理员接口层
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Slf4j
@Controller
@RequestMapping("/admin/cache")
@Api(value = "缓存管理接口 - Controller", description = "缓存管理接口", tags = {"缓存管理接口"})
public class CacheController {

	@Autowired
	private CacheService cacheService;

	@ApiOperation(value = "清空全部缓存", notes = "清空全部缓存")
	@GetMapping("/allCache")
	@ResponseBody
	@IgnoreAuthority
	public Message<?> allCache(){
		cacheService.clearAll();
		return Message.success("清空成功");
	}

	@ApiOperation(value = "清空其他缓存", notes = "清空其他缓存")
	@GetMapping("/otherCache")
	@ResponseBody
	@IgnoreAuthority
	public Message<?> otherCache(){
		return Message.success("清空成功");
	}
}