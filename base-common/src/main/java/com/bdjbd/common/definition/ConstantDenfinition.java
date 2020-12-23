package com.bdjbd.common.definition;

/**
  * @interfaceName ConstantDenfinition
  * @description 常量定义
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public interface ConstantDenfinition {

    /**
     * 手机正则表达式
     */
    String MOBILE_REGEX = "(^[1][3-9]\\d{9}$)";

    /**
     * 通讯号码正则表达式
     */
    String PHONE_REGEX = "(^[1][3-8]\\d{9}$|^0\\d{2,3}-\\d{5,9}$|^\\d{5,9}$)";

    /**
     * 数字 : 表达式
     */
    String NUMBER_REGEX = "([^0-9:])";

    /**
     * 字符串 ("true")
     */
    String TRUE_STR = "true";

    /**
     * 字符串 (" ")
     */
    String STR_SPACE = " ";

    /**
     * 字符串 ("")
     */
    String STR_EMPTY = "";

    /**
     * 字符串 ("[")
     */
    String STR_LEFT_BRACE = "[";

    /**
     * 字符串 ("]")
     */
    String STR_RIGHT_BRACE = "]";

    /**
     * 字符串 ("1")
     */
    String STR_NUM_ONE = "1";

    /**
     * 字符串(,)
     */
    String COMMA = ",";

    /**
     * 字符串(:)
     */
    String COLON = ":";
}
