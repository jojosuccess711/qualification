package com.bdjbd.service.sms;

import com.bdjbd.common.SmsConstant;
import com.bdjbd.common.util.DateUtil;
import com.bdjbd.service.common.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhuzhe
 * @date 2019/10/29 14:15
 * @email zhuzhe_mail@163.com
 */
@Component
@Slf4j
public class SmsServerService {

    /**
     * 短信限制类型
     */
    public enum Type {

        /**
         * 登录注册（redis缓存中短信发送次数key的前缀）
         */
        REDIS_SEND_SMS_COUNT_PREFIX
    }

    @Autowired
    private RedisService redisService;

    /**
     * 是否可发送短信
     *
     * @param openid
     * @return
     */
    public Boolean isSendSms(String openid, Type type) {
        if (StringUtils.isBlank(openid) || type == null)
            return false;

        String key = type.name() + openid;
        Integer count = getCurrentDayCount(key, type);
        if (SmsConstant.sendVerifyCodeCount > count) {
            updateCurrentDayCount(key, count, type);
            return true;
        }
        return false;
    }

    /**
     * 获取当天发送次数
     *
     * @param key
     * @return
     */
    private Integer getCurrentDayCount(String key, Type type) {
        try {
            Object value = redisService.getForValue(key);
            if (value == null || StringUtils.isBlank(value.toString())) {
                return 0;
            }
            String[] data = value.toString().substring(type.name().length()).split("_");
            if (!DateUtil.formatYMD(null).equals(data[0])) {
                return 0;
            }
            return Integer.valueOf(data[1]);
        } catch (Exception e) {
            log.error("redis 获取制定key值异常 --> key = {}", key, e);
            return 0;
        }
    }

    /**
     * 更新redis数据
     *
     * @param key
     * @param count
     */
    private void updateCurrentDayCount(String key, Integer count, Type type) {
        String value = type.name() + DateUtil.formatYMD(null) + "_" + (++count);
        Long overtimeSeconds = DateUtil.getDifftimeSecond(null, DateUtil.getDateMax(null));
        redisService.setForValue(key, value, overtimeSeconds.longValue());
    }
}
