package com.bdjbd.common.exception;

import java.io.Serializable;

/**
  * @className BaseException
  * @description 异常基类
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public class BaseException extends RuntimeException implements Serializable {

    /**
     * 状态（默认=200）
     */
    private int code = 200;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 无参构造方法
     */
    public BaseException() {
    }

    /**
     * 全参构造方法
     * @param message 消息内容
     * @param code 状态
     */
    public BaseException(String message, int code) {
        super(message);
        this.code = code;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
