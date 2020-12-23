package com.bdjbd.common.constatns;

/**
  * @className CommonConstants
  * @description 常量定义
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public class CommonConstants {

    /**
     * token 异常
     */
    public static final Integer EX_TOKEN_ERROR_CODE = 40101;
    /**
     * 用户token异常
     */
    public static final Integer EX_USER_INVALID_CODE = 40102;
    /**
     * 客户端token异常
     */
    public static final Integer EX_CLIENT_INVALID_CODE = 40131;
    public static final Integer EX_CLIENT_FORBIDDEN_CODE = 40331;
    /**
     * 其他异常错误码
     */
    public static final Integer EX_OTHER_CODE = 500;

    //context key 定义
    public static final String CONTEXT_KEY_USER_ID = "currentUserId";
    public static final String CONTEXT_KEY_USERNAME = "currentUserName";
    public static final String CONTEXT_KEY_USER_NAME = "currentUser";
    public static final String CONTEXT_KEY_USER_TOKEN = "currentUserToken";

    //jwt key 定义
    public static final String JWT_KEY_USER_ID = "userId";
    public static final String JWT_KEY_NAME = "name";
}
