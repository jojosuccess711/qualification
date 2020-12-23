package com.bdjbd.enums;

import com.bdjbd.common.common.BaseEnum;

/**
 * @author dbc
 * @version 1.0
 * @className ItemType
 * @description TODO
 * @date 2020/3/2
 **/
public enum ItemTypeExpress implements BaseEnum<String> {

    GT(">"),

    GE(">="),

    LT("<"),

    LE("<="),

    EQ("="),

    IN("[]");

    private String key;

    public String getKey(){
        return key;
    }

    ItemTypeExpress(String key){
        this.key = key;
    }
}
