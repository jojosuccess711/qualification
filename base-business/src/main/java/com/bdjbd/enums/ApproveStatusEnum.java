package com.bdjbd.enums;

public enum ApproveStatusEnum {
    /**
     * 未通过
     */
    NOPASS(0),

    /**
     * 通过
     */
    PAST(1),
    /**
     * 审核中
     */
    UNDERREVIEW(2);

    private Integer key;

    ApproveStatusEnum(Integer key) {
        this.key = key;
    }

    public Integer getKey() {
        return this.key;
    }
}

