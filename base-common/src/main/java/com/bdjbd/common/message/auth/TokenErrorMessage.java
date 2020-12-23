package com.bdjbd.common.message.auth;

import com.bdjbd.Message;
import com.bdjbd.common.constant.RestCodeConstants;

/**
  * @className TokenErrorMessage
  * @description token错误消息
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public class TokenErrorMessage extends Message<String> {

    public TokenErrorMessage(String message) {
        super(RestCodeConstants.TOKEN_ERROR_CODE, Message.ERROR, message, null);
    }
}
