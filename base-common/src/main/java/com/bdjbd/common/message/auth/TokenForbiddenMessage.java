package com.bdjbd.common.message.auth;

import com.bdjbd.Message;
import com.bdjbd.common.constant.RestCodeConstants;

/**
  * @className TokenForbiddenMessage
  * @description token 禁止消息
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public class TokenForbiddenMessage extends Message<String> {

    public TokenForbiddenMessage(String message) {
        super(RestCodeConstants.TOKEN_FORBIDDEN_CODE, Message.ERROR, message, null);
    }
}
