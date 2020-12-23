package com.bdjbd.common.exception.auth;


import com.bdjbd.common.constant.CommonConstants;
import com.bdjbd.common.exception.BaseException;

/**
  * @className ClientInvalidException
  * @description 客户端无效异常
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public class ClientInvalidException extends BaseException {

    /**
     * 构造方法
     * @param message 信息内容
     */
    public ClientInvalidException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
