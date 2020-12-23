package com.bdjbd.exception;

/**
  * @className JwtTokenExpiredException
  * @description JWT token过期异常
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public class JwtTokenExpiredException extends Exception {

    public JwtTokenExpiredException(String s) {
        super(s);
    }
}
