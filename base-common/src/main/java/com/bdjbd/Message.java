package com.bdjbd;

import java.io.Serializable;

/**
  * @className Message
  * @description 消息协议
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public class Message<T> implements Serializable {

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String EXCEPTION = "exception";

    public static final Integer DEFAULT_ERROR_CODE = 0;
    public static final Integer DEFAULT_SUCCESS_CODE = 200;
    public static final Integer DEFAULT_EXCEPTION_CODE = 500;

    private Integer code;

    private String status;

    private String message;

    private T data;

    public Message() {
    }

    public Message(Integer code, String status, String message, T data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static Message success(){
        return new Message(DEFAULT_SUCCESS_CODE, SUCCESS, null, null);
    }

    public static Message success(String message){
        return new Message(DEFAULT_SUCCESS_CODE, SUCCESS, message, null);
    }

    public static <T> Message success(T data){
        return new Message(DEFAULT_SUCCESS_CODE, SUCCESS, null, data);
    }

    public static <T> Message success(String message, T data){
        return new Message(DEFAULT_SUCCESS_CODE, SUCCESS, message, data);
    }

    public static <T> Message success(Integer code, String message){
        return new Message(code, SUCCESS, message, null);
    }

    public static <T> Message success(Integer code, String message, T data){
        return new Message(code, SUCCESS, message, data);
    }

    public static Message error(String message){
        return new Message(DEFAULT_ERROR_CODE, ERROR, message, null);
    }

    public static <T> Message error(T data){
        return new Message(DEFAULT_ERROR_CODE, ERROR, null, data);
    }

    public static <T> Message error(String message, T data){
        return new Message(DEFAULT_ERROR_CODE, ERROR, message, data);
    }

    public static <T> Message error(Integer code, String message){
        return new Message(code, ERROR, message, null);
    }

    public static <T> Message error(Integer code, String message, T data){
        return new Message(code, ERROR, message, data);
    }

    public static Message exception(String message){
        return new Message(DEFAULT_EXCEPTION_CODE, EXCEPTION, message, null);
    }

    public static <T> Message exception(T data){
        return new Message(DEFAULT_EXCEPTION_CODE, EXCEPTION, null, data);
    }

    public static <T> Message exception(String message, T data){
        return new Message(DEFAULT_EXCEPTION_CODE, EXCEPTION, message, data);
    }

    public static <T> Message exception(Integer code, String message){
        return new Message(code, EXCEPTION, message, null);
    }

    public static <T> Message exception(Integer code, String message, T data){
        return new Message(code, EXCEPTION, message, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean isSuccess(){
        return SUCCESS.equals(getStatus());
    }
}
