package com.bdjbd.common.exception.auth;


import com.bdjbd.common.constant.CommonConstants;
import com.bdjbd.common.exception.BaseException;

/**
  * @className UserTokenException
  * @description 用户token异常
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public class UserTokenException extends BaseException {

    /**
     * 构造方法
     * @param message 消息内容
     */
    public UserTokenException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
