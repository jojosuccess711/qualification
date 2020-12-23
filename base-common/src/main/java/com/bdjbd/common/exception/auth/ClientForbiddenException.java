package com.bdjbd.common.exception.auth;


import com.bdjbd.common.constant.CommonConstants;
import com.bdjbd.common.exception.BaseException;

/**
  * @className ClientForbiddenException
  * @description 客户端禁止访问异常
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public class ClientForbiddenException extends BaseException {

    /**
     * 构造方法
     * @param message 信息内容
     */
    public ClientForbiddenException(String message) {
        super(message, CommonConstants.EX_CLIENT_FORBIDDEN_CODE);
    }

}
