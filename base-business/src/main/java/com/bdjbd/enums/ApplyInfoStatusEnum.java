package com.bdjbd.enums;

public enum ApplyInfoStatusEnum {
    /**
     * 保存
     */
    SAVE(0),

    /**
     * 提交审核
     */
    SUMMIT(1);

    private Integer key;

    ApplyInfoStatusEnum(Integer key) {
        this.key = key;
    }

    public Integer getKey() {
        return this.key;
    }
}
