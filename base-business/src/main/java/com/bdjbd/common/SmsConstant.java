package com.bdjbd.common;

public interface SmsConstant {

    /**
     * 短信限制类型
     */
    public enum Type {

        /**
         * 登录注册（redis缓存中短信发送次数key的前缀）
         */
        REDIS_SEND_SMS_COUNT_PREFIX
    }

    String REGISTER_VERIFY_CODE_REDIS_KEY_PREFIX = "REGISTER_VERIFY_CODE_";

    String RESET_VERIFY_CODE_REDIS_KEY_PREFIX = "RESET_VERIFY_CODE_";

    Integer sendVerifyCodeCount = 100;
}
