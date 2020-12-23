package com.bdjbd.enums;

import com.bdjbd.common.common.BaseEnum;

/**
 * @author dbc
 * @version 1.0
 * @className ItemType
 * @description TODO
 * @date 2020/3/2
 **/
public enum ItemType implements BaseEnum<String> {

    SELECT("select"),

    SELECT_AND_TEXT("select&text"),

    RADIO("radio"),

    TEXT("text"),

    TEXTAREA("textarea"),

    DATE("date");

    private String key;

    public String getKey(){
        return key;
    }

    ItemType(String key){
        this.key = key;
    }
}
