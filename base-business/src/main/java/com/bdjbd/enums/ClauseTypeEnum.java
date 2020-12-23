package com.bdjbd.enums;

public enum ClauseTypeEnum {
    /**
     * 否
     */
    NO(0),
    /**
     * 是
     */
    YES(1),
    /**
     * 其中之一
     */
    ONE(2);

    private Integer key;

    ClauseTypeEnum(Integer key) {
        this.key = key;
    }

    public Integer getKey() {
        return this.key;
    }
}
