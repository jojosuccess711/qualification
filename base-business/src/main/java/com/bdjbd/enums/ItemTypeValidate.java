package com.bdjbd.enums;

import com.bdjbd.common.common.BaseEnum;

/**
 * @author dbc
 * @version 1.0
 * @className ItemType
 * @description TODO
 * @date 2020/3/2
 **/
public enum ItemTypeValidate implements BaseEnum<String> {

    NAME("name"),

    TEXT("text"),

    CONTAINS("contains"),

    NEAR("near"),

    NUMBER("number"),

    AVG_NUMBER("avg_number"),

    AVG_NUMBER_YEAR("avg_number_year"),

    SUM("sum"),

    RECORD("record");


    private String key;

    public String getKey(){
        return key;
    }

    ItemTypeValidate(String key){
        this.key = key;
    }
}
