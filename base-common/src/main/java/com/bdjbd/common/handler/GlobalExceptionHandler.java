package com.bdjbd.common.handler;

import com.bdjbd.Message;
import com.bdjbd.common.exception.BaseException;
import com.bdjbd.common.exception.auth.ClientTokenException;
import com.bdjbd.common.exception.auth.UserInvalidException;
import com.bdjbd.common.exception.auth.UserTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
  * @className GlobalExceptionHandler
  * @description 全局异常处理器
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 客户端token异常
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler(ClientTokenException.class)
    public Message<String> clientTokenExceptionHandler(HttpServletResponse response, ClientTokenException ex) {
        response.setStatus(403);
        log.error(ex.getMessage(), ex);
        return Message.error(ex.getCode(), ex.getMessage());
    }

    /**
     * 用户token异常
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler(UserTokenException.class)
    public Message<String> userTokenExceptionHandler(HttpServletResponse response, UserTokenException ex) {
        response.setStatus(200);
        log.error(ex.getMessage(), ex);
        return Message.error(ex.getCode(), ex.getMessage());
    }

    /**
     * 用户无效异常
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler(UserInvalidException.class)
    public Message<String> userInvalidExceptionHandler(HttpServletResponse response, UserInvalidException ex) {
        response.setStatus(200);
        log.error(ex.getMessage(), ex);
        return Message.error(ex.getCode(), ex.getMessage());
    }

    /**
     * 默认异常
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public Message<String> baseExceptionHandler(HttpServletResponse response, BaseException ex) {
        response.setStatus(500);
        log.error(ex.getMessage(), ex);
        return Message.error(ex.getCode(), ex.getMessage());
    }

    /**
     * 其他异常
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Message<String> otherExceptionHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(500);
        log.error(ex.getMessage(), ex);
        return Message.error(Message.DEFAULT_EXCEPTION_CODE, ex.getMessage());
    }

}
