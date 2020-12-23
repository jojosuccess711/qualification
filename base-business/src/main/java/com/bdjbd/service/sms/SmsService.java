package com.bdjbd.service.sms;

import com.alibaba.fastjson.JSON;
import com.bdjbd.dao.entity.SysConfig;
import com.bdjbd.service.sys.SysConfigService;
import com.bdjbd.util.SmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.bdjbd.common.SysConfigConstant.SMS_API_KEY;

/**
 * @Author: dbc
 * @Date: 2018/7/2
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Component
public class SmsService {

    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 短信发送
     *
     * @param content
     * @param mobile
     * @return
     */
    public Boolean sendMessage(String content, String mobile) {
        String resultJson = SmsUtil.sendSms(getSmsApiKey(), content, mobile);
        Map<String, Object> resultMap = (Map<String, Object>) JSON.parse(resultJson);
        if (resultMap == null || resultMap.get("code") == null) {
            log.info("短信发送失败,失败原因: 数据返回空");
            return false;
        }
        if (!"0".equals(resultMap.get("code").toString())) {
            log.info("短信发送失败,失败原因:" + resultMap.get("msg").toString());
            return false;
        }
        return true;
    }

    private String getSmsApiKey() {
        SysConfig sysConfig = sysConfigService.findHasAttributes(SMS_API_KEY);
        return sysConfig.getValue();
    }

    /**
     * 短信批量发送
     *
     * @param content
     * @param mobile
     * @return
     */
    public Boolean batchSendMessage(String content, String mobile) {
        String resultJson = SmsUtil.batchSendSms(getSmsApiKey(), content, mobile);
        Map<String, Object> resultMap = (Map<String, Object>) JSON.parse(resultJson);
        if (resultMap == null || resultMap.get("code") == null) {
            log.error("短信发送失败,失败原因: 数据返回空,content:{},mobile:{}", content, mobile);
            return false;
        }
        if (!"0".equals(resultMap.get("code").toString())) {
            log.error("短信发送失败,失败原因:" + resultMap.get("msg").toString());
            log.error("content:{},mobile:{}", content, mobile);
            return false;
        }
        return true;
    }


}
