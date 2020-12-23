package com.bdjbd.common.exception.auth;


import com.bdjbd.common.constant.CommonConstants;
import com.bdjbd.common.exception.BaseException;

/**
  * @className UserInvalidException
  * @description 用户非法异常
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public class UserInvalidException extends BaseException {

    /**
     * 构造方法
     * @param message 消息内容
     */
    public UserInvalidException(String message) {
        super(message, CommonConstants.EX_USER_PASS_INVALID_CODE);
    }
}
