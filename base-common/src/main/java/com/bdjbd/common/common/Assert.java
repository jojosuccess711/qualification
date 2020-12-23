package com.bdjbd.common.common;

import com.bdjbd.common.exception.CheckedException;
import org.apache.commons.lang3.StringUtils;

/**
  * @className Assert
  * @description 断言
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public final class Assert {

    /**
     * 字符串断言为空
     * @param str 字符串
     * @param message 异常抛出消息内容
     */
    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new CheckedException(message);
        }
    }

    /**
     *  对象断言为 NULL
     * @param object 对象
     * @param message 异常抛出消息内容
     */
    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new CheckedException(message);
        }
    }
}
