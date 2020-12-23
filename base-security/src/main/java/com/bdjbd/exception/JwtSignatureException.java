package com.bdjbd.exception;

/**
  * @className JwtSignatureException
  * @description JWT 签名异常
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public class JwtSignatureException extends Exception {

    public JwtSignatureException(String s) {
        super(s);
    }
}
