package com.bdjbd.common.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Random;

/**
  * @className StringUtils
  * @description 字符串工具类
  * @author dbc
  * @date 2019/7/10
  * @version 1.0
  **/
public final class StringUtil {

    /**
     * 编码 UTF-8
     */
    public static final String UTF_8 = "UTF-8";

    public static final String FOUR_REPLACE = "?";

    public static final String FIVE_REPLACE = "�";

    public static final String SIX_REPLACE = "‥";

    /***
     * 生成随机字符串
     *
     * @param length 生成字符串长度
     * @return 随机字符串
     */
    public static String getRandomString(Integer length) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getObjectValue(Object obj){
        return obj == null ? "" : obj.toString();
    }

    /**
     * 获取utf-8字符串
     *
     * @param text
     * @return
     */
    public static String getUTF8Character(String text) {
        if (text == null) {
            return "";
        }
        try {
            byte[] bytes = text.getBytes(UTF_8);
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            int i = 0;
            while (i < bytes.length) {
                short b = bytes[i];
                if (b > 0) {
                    buffer.put(bytes[i++]);
                    continue;
                }
                b += 256;
//				if ((b ^ 0xC0) >> 4 == 0) {  
//					buffer.put(bytes, i, 2);  
//					i += 2;  
//				}  
//				else if ((b ^ 0xE0) >> 4 == 0) {  
//					buffer.put(bytes, i, 3);  
//					i += 3;  
//				}  
//				else if ((b ^ 0xF0) >> 4 == 0) {  
//					i += 4;  
//				}
                if (((b >> 5) ^ 0x06) == 0) {
                    buffer.put(bytes, i, 2);
                    i += 2;
                } else if (((b >> 4) ^ 0x0E) == 0) {
                    buffer.put(bytes, i, 3);
                    i += 3;
                } else if (((b >> 3) ^ 0x1E) == 0) {
                    i += 4;
                    buffer.put(FOUR_REPLACE.getBytes(UTF_8));
                } else if (((b >> 2) ^ 0xBE) == 0) {
                    i += 5;
                    buffer.put(FIVE_REPLACE.getBytes(UTF_8));
                } else {
                    i += 6;
                    buffer.put(SIX_REPLACE.getBytes(UTF_8));
                }
            }
            buffer.flip();
            return new String(buffer.array(), UTF_8).trim();
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
