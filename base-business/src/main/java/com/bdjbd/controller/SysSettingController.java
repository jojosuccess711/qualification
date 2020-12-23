/**
 * @filename:SysAdminController 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd.
 * All right reserved.
 */
package com.bdjbd.controller;

import com.bdjbd.Message;
import com.bdjbd.common.util.FieldValidateUtil;
import com.bdjbd.service.sys.SysAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @Description: 管理员接口层
 * @Author: DBC
 * @CreateDate: 2019/12/03
 * @Version: V1.0
 *
 */
@Slf4j
@RestController
@RequestMapping("/admin/setting")
@Api(value = "系统设置接口 - Controller", description = "系统设置接口", tags = {"系统设置接口"})
public class SysSettingController {

    @Autowired
    SysAdminService adminService;

    @ApiOperation(value = "账号信息接口", notes = "账号信息接口")
    @GetMapping("/info")
    public Message<?> info(@RequestHeader("Authorization") String token) {
        String userId = adminService.findUserIdByToken(token);
        return Message.success(adminService.info(userId).getData());
    }

    @ApiOperation(value = "更新密码接口", notes = "更新密码接口")
    @GetMapping("/updatePassword")
    @ResponseBody
    public Message<?> updatePassword(@RequestHeader("Authorization") String token, String oldPassword, String newPassword) {
        String userId = adminService.findUserIdByToken(token);

        if (StringUtils.isBlank(oldPassword))
            return Message.error("旧密码不可为空");
        if (StringUtils.isBlank(newPassword))
            return Message.error("新密码不可为空");
        if (!FieldValidateUtil.isPassword(newPassword))
            return Message.error("密码必须由4-20位数字和字母组成");

        return adminService.updatePassword(userId, oldPassword, newPassword);
    }
}