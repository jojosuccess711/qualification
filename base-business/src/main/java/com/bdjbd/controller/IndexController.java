package com.bdjbd.controller;

import com.bdjbd.Message;
import com.bdjbd.annotation.IgnoreAuthority;
import com.bdjbd.annotation.IgnoreUserToken;
import com.bdjbd.common.util.FieldValidateUtil;
import com.bdjbd.dao.entity.SysAdmin;
import com.bdjbd.service.sys.IndexService;
import com.bdjbd.service.sys.SysAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: dbc
 * @Date: 2018/8/16
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/open/register")
@Api(value = "账号注册接口 - Controller", description = "账号注册接口", tags = {"账号注册接口"})
public class IndexController {

    @Autowired
    private SysAdminService adminService;

    @Autowired
    private IndexService indexService;

    /**
     * 短信验证码发送接口
     *
     * @param mobile 手机号
     * @return
     */
    @ApiOperation(value = "短信验证码接口", notes = "发送验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号码", paramType = "query", dataType = "String", example = "13402941409"),
            @ApiImplicitParam(name = "type", value = "短息类型 0 注册 1 找回密码", paramType = "query", dataType = "String", example = "0"),
            @ApiImplicitParam(name = "imageCode", value = "图形验证码", paramType = "query", dataType = "String", example = "0")
    })
    @PostMapping("/sendSmsCode")
    @IgnoreUserToken
    @IgnoreAuthority
    public Message sendSms(String mobile, String type, String imageCode, HttpServletRequest request) {
        if (org.springframework.util.StringUtils.isEmpty(mobile))
            return Message.error("请输入手机号");
        if (!FieldValidateUtil.isMobile(mobile))
            return Message.error("请您输入正确的手机号码");
        if (StringUtils.isBlank(imageCode))
            return Message.error("图形验证码不可为空");
        try {
            String storeImageCode = adminService.getImageCode(request.getSession().getId());
            if (!imageCode.equalsIgnoreCase(storeImageCode))
                return Message.error("图形验证码错误");

            if (StringUtils.isBlank(type) || StringUtils.equals("0", type)) {
                SysAdmin old = adminService.findByUsername(mobile);
                if (old != null) {
                    return Message.error("手机号已注册，可直接登录");
                }
            }
            return indexService.sendSms(mobile, type);
        } catch (Exception e) {
            log.error("短信发送异常,{}", e);
        }
        return Message.exception("短信发送失败");
    }

    /**
     * 使用手机号实现注册功能     *
     *
     * @param mobile 用户手机号
     * @param code   手机验证码
     * @return
     */
    @ApiOperation(value = "用户注册接口", notes = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号码", paramType = "query", dataType = "String", example = "15529396626"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", dataType = "String", example = "1234"),
            @ApiImplicitParam(name = "code", value = "验证码", paramType = "query", dataType = "String", example = "123456"),
            @ApiImplicitParam(name = "name", value = "昵称", paramType = "query", dataType = "String", example = "张三"),
            @ApiImplicitParam(name = "techNum", value = "编号", paramType = "query", dataType = "String", example = "10001000")
    })
    @PostMapping("/register")
    @IgnoreUserToken
    @IgnoreAuthority
    public Message register(String mobile, String password, String code, String name, String techNum) {
        try {
            return indexService.register(mobile, password, code, name, techNum);
        } catch (Exception e) {
            log.error("注册失败，{}", e);
        }
        return Message.error("注册失败");
    }

    /**
     * 使用校园网账号实现注册功能      *
     *
     * @param mobile 校园网账号
     * @return
     */
    @ApiOperation(value = "用户注册接口", notes = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "校园网账号", paramType = "query", dataType = "String", example = "15529396626"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", dataType = "String", example = "1234"),
            @ApiImplicitParam(name = "name", value = "昵称", paramType = "query", dataType = "String", example = "张三"),
            @ApiImplicitParam(name = "techNum", value = "编号", paramType = "query", dataType = "String", example = "10001000")
    })
    @PostMapping("/register2")
    @IgnoreUserToken
    @IgnoreAuthority
    public Message register2(String mobile, String password, String name, String techNum) {
        try {
            return indexService.register2(mobile, password, name, techNum);
        } catch (Exception e) {
            log.error("注册失败，{}", e);
        }
        return Message.error("注册失败");
    }


    /**
     * 密码重置接口接口
     *
     * @param mobile 手机号
     * @return
     */
    @ApiOperation(value = "密码重置接口", notes = "密码重置接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号码", paramType = "query", dataType = "String", example = "15529396626"),
            @ApiImplicitParam(name = "code", value = "验证码", paramType = "query", dataType = "String", example = "123456"),
    })
    @PostMapping("/resetPassword")
    @IgnoreUserToken
    @IgnoreAuthority
    public Message resetPassword(String mobile, String code) {
        try {
            return indexService.resetPassword(mobile, code);
        } catch (Exception e) {
            log.error("密码重置失败，{}", e);
        }
        return Message.error("密码重置失败，请重试！");
    }

    /**
     * 专家相关
     * <p>
     * 注册，删除，重置密码，查询
     */

    @ApiOperation(value = "专家注册接口", notes = "专家注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "校园网账号", paramType = "query", dataType = "String", example = "15529396626"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", dataType = "String", example = "1234"),
            @ApiImplicitParam(name = "name", value = "昵称", paramType = "query", dataType = "String", example = "张三"),
            @ApiImplicitParam(name = "department", value = "部门", paramType = "query", dataType = "String", example = "专家部门")
    })
    @PostMapping("/register3")
    @IgnoreUserToken
    @IgnoreAuthority
    public Message register3(String mobile, String department, String name) {
        try {
            return indexService.register3(mobile, department, name);
        } catch (Exception e) {
            log.error("专家注册失败，{}", e);
        }
        return Message.error("专家注册失败");
    }

    @ApiOperation(value = "专家列表接口", notes = "专家列表注册")
    @PostMapping("/findExpert")
    @IgnoreUserToken
    @IgnoreAuthority
    public Message findExpert(String name, Integer pageNum, Integer pageSize) {
        return Message.success(adminService.findExpert(name, pageNum, pageSize));
    }

    @ApiOperation(value = "删除专家接口", notes = "删除专家接口")
    @PostMapping("/delExpert")
    @IgnoreUserToken
    @IgnoreAuthority
    public Message delExpert(String id) {
        Integer result = adminService.delExpert(id);
        if (result > 0)
            return Message.success("删除专家成功");
        else
            return Message.error("删除专家失败");
    }

    @ApiOperation(value = "修改专家接口", notes = "修改专家接口")
    @PostMapping("/updateExpert")
    @IgnoreUserToken
    @IgnoreAuthority
    public Message updateExpert(SysAdmin admin) {

        Integer result = adminService.updateExpert(admin);
        if (result > 0)
            return Message.success("修改专家信息成功");
        else
            return Message.error("修改专家信息成功");
    }
}
