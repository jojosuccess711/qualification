package com.bdjbd.common.constant;

/**
  * @interfaceName CommonConstants
  * @description 通用常量定义
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public interface CommonConstants {

    // 用户token异常
    Integer EX_USER_INVALID_CODE = 40101;
    Integer EX_USER_PASS_INVALID_CODE = 40001;

    //无权限
    Integer NO_AUTHORITY = 40201;

    // 客户端token异常
    Integer EX_CLIENT_INVALID_CODE = 40301;
    Integer EX_CLIENT_FORBIDDEN_CODE = 40331;
    Integer EX_OTHER_CODE = 500;
    String CONTEXT_KEY_USER_ID = "currentUserId";
    String CONTEXT_KEY_USERNAME = "currentUserName";
    String CONTEXT_KEY_USER_NAME = "currentUser";
    String CONTEXT_KEY_USER_TOKEN = "currentUserToken";
    String JWT_KEY_USER_ID = "userId";
    String JWT_KEY_NAME = "name";
}
