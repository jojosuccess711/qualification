package com.bdjbd.service.sys;

import com.bdjbd.Message;
import com.bdjbd.common.util.FieldValidateUtil;
import com.bdjbd.common.util.NumberUtil;
import com.bdjbd.common.util.VerifyCodeUtil;
import com.bdjbd.dao.entity.SysAdmin;
import com.bdjbd.dao.entity.SysConfig;
import com.bdjbd.service.QaUserInfoService;
import com.bdjbd.service.common.RedisService;
import com.bdjbd.service.sms.SmsServerService;
import com.bdjbd.service.sms.SmsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

import static com.bdjbd.common.SmsConstant.REGISTER_VERIFY_CODE_REDIS_KEY_PREFIX;
import static com.bdjbd.common.SmsConstant.RESET_VERIFY_CODE_REDIS_KEY_PREFIX;
import static com.bdjbd.common.SysConfigConstant.SMS_MOBILE_CAPTCHA_REGISTER;
import static com.bdjbd.common.SysConfigConstant.SMS_MOBILE_RESET_PASSWORD;

/**
 * @author zhuzhe
 * @date 2020/4/13 15:51
 * @email zhuzhe_mail@163.com
 */
@Transactional
@Service
public class IndexService {

    @Autowired
    private SysAdminService adminService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private SmsServerService smsServerService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private QaUserInfoService userInfoService;

    public Message sendSms(String mobile, String type) {

        if (!smsServerService.isSendSms(mobile, SmsServerService.Type.REDIS_SEND_SMS_COUNT_PREFIX))
            return Message.error("今日短信发送次数已超限，请明日再来！");

        String verifyRedisKey;
        if ("1".equals(type)) {
            verifyRedisKey = RESET_VERIFY_CODE_REDIS_KEY_PREFIX + mobile;
        } else {
            verifyRedisKey = REGISTER_VERIFY_CODE_REDIS_KEY_PREFIX + mobile;
        }
        //发送短信验证码
        String code = VerifyCodeUtil.createSmsVerifyCode();
        SysConfig sysConfig = sysConfigService.findHasAttributes(SMS_MOBILE_CAPTCHA_REGISTER);
        if (sysConfig == null || !sysConfig.getStatus())
            return Message.error("配置有误");
        String content = sysConfig.getValue().replace("#code", code);
        Boolean aBoolean = smsService.sendMessage(content, mobile);
        if (aBoolean) {
            redisService.setForValue(verifyRedisKey, code, 600L);
            return Message.success();
        }
        return Message.error("短信发送失败");
    }

    public Message register(String mobile, String password, String code, String name, String techNum) {
        if (StringUtils.isEmpty(mobile))
            return Message.error("请输入手机号");
        if (!FieldValidateUtil.isMobile(mobile))
            return Message.error("请您输入正确的手机号码");
        if (StringUtils.isBlank(password))
            return Message.error("请输入密码");
        if (StringUtils.isBlank(password))
            return Message.error("请输入验证码");
        if (StringUtils.isBlank(name))
            return Message.error("姓名不可为空");
        if (name.length() > 100)
            return Message.error("姓名不可大于100个字符");
        if (StringUtils.isNotBlank(techNum) && techNum.length() > 50)
            return Message.error("编号不可大于100个字符");

        SysAdmin old = adminService.findByUsername(mobile);
        if (old != null) {
            return Message.error("手机号已注册，可直接登录");
        }

        Object oriCode = redisService.getForValue(REGISTER_VERIFY_CODE_REDIS_KEY_PREFIX + mobile);
        if (oriCode == null || org.springframework.util.StringUtils.isEmpty(oriCode.toString()))
            return Message.error("验证码过期或无效");
        if (!oriCode.equals(code))
            return Message.error("验证码错误，请重新输入");

        Message<SysAdmin> sysAdminMessage = adminService.defaultCreate(mobile, name, password);
        SysAdmin admin = sysAdminMessage.getData();
        userInfoService.createHandler(admin.getId(), mobile, name, techNum);

        redisService.delete(REGISTER_VERIFY_CODE_REDIS_KEY_PREFIX + mobile);
        return Message.success("注册成功");
    }

    public Message register2(String mobile, String password, String name, String techNum) {
        if (StringUtils.isEmpty(mobile))
            return Message.error("请输入校园网账号");
        /*校园网账号10位，前5位90019，输入不是90019不通过*/
        boolean matches = Pattern.matches("90019[0-9]{5}", mobile);
        if (!matches)
            return Message.error("请您输入正确的校园网账号(90019*****)");
        if (StringUtils.isBlank(password))
            return Message.error("请输入密码");
        if (StringUtils.isBlank(name))
            return Message.error("姓名不可为空");
        if (name.length() > 100)
            return Message.error("姓名不可大于100个字符");
        if (StringUtils.isNotBlank(techNum) && techNum.length() > 50)
            return Message.error("编号不可大于100个字符");

        SysAdmin old = adminService.findByUsername(mobile);
        if (old != null) {
            return Message.error("账号已注册，可直接登录");
        }

        Message<SysAdmin> sysAdminMessage = adminService.defaultCreate(mobile, name, password);
        SysAdmin admin = sysAdminMessage.getData();
        userInfoService.createHandler(admin.getId(), mobile, name, techNum);
        return Message.success("注册成功");
    }

    public Message register3(String mobile, String department, String name) {
        if (StringUtils.isEmpty(mobile))
            return Message.error("请输入校园网账号");
        /*校园网账号10位，前5位10001，输入不是10001不通过*/
        boolean matches = Pattern.matches("10001[0-9]{5}", mobile);
        if (!matches)
            return Message.error("请您输入正确的账号(10001*****)");
//        if (StringUtils.isBlank(password))
//            return Message.error("请输入密码");
        if (StringUtils.isBlank(name))
            return Message.error("姓名不可为空");
        if (name.length() > 100)
            return Message.error("姓名不可大于100个字符");

        SysAdmin old = adminService.findByUsername(mobile);
        if (old != null) {
            return Message.error("账号已注册，可直接登录");
        }
        adminService.defaultCreate1(mobile, name, department);
        return Message.success("专家注册成功");
    }

    public Message resetPassword(String mobile, String code) {
        if (StringUtils.isEmpty(mobile))
            return Message.error("请输入手机号");
        if (!FieldValidateUtil.isMobile(mobile))
            return Message.error("请您输入正确的手机号码");
        SysAdmin old = adminService.findByUsername(mobile);
        if (old == null)
            return Message.error("手机号未注册");

        Object oriCode = redisService.getForValue(RESET_VERIFY_CODE_REDIS_KEY_PREFIX + mobile);
        if (oriCode == null || org.springframework.util.StringUtils.isEmpty(oriCode.toString()))
            return Message.error("验证码过期或无效");
        if (!oriCode.equals(code))
            return Message.error("验证码错误请重新输入");

        Integer password = NumberUtil.randomFourNumber();
        adminService.resetPassword(old.getId());

        SysConfig sysConfig = sysConfigService.findHasAttributes(SMS_MOBILE_RESET_PASSWORD);
        if (sysConfig == null || !sysConfig.getStatus()) {
        } else {
            String content = sysConfig.getValue().replace("#code", password.toString());
            Boolean aBoolean = smsService.sendMessage(content, mobile);
            if (!aBoolean) {
                return Message.error("密码重置失败，请重试！");
            }
        }

        redisService.delete(RESET_VERIFY_CODE_REDIS_KEY_PREFIX + mobile);
        return Message.success("密码已重置为: " + password + ", 请妥善保管！", password);
    }

}
