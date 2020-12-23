package com.bdjbd.exception;

/**
  * @className JwtIllegalArgumentException
  * @description JWT 非法参数异常
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public class JwtIllegalArgumentException extends Exception {

    public JwtIllegalArgumentException(String s) {
        super(s);
    }
}
