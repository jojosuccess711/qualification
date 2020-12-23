package com.bdjbd.enums;

public enum CategoryTypeEnum {
    /**
     * 领域
     */
    FIELD(0),
    /**
     * 岗位
     */
    POST(1),
    /**
     * 职称
     */
    ACADEMIC(2);

    private Integer key;

    CategoryTypeEnum(Integer key) {
        this.key = key;
    }

    public Integer getKey() {
        return this.key;
    }
}
