package com.bdjbd.common.util;

import java.util.Random;

public class VerifyCodeUtil {
    public static String createSmsVerifyCode() {
        // 创建一个随机数生成器类。
        Random random = new Random();
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();

        // 设置默认生成4位验证码
        int length = 4;
        // 设置备选验证码:包括"a-z"和数字"0-9"
//        String base = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789";
        String base = "1234567890";

        int size = base.length();

        // 随机产生4位数字的验证码。
        for (int i = 0; i < length; i++) {
            // 得到随机产生的验证码数字。
            int start = random.nextInt(size);
            String strRand = base.substring(start, start + 1);
            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
        return randomCode.toString();
    }
}
