package com.bdjbd.enums;

/**
 * @author zhuzhe
 * @date 2020/3/25 14:46
 * @email zhuzhe_mail@163.com
 */
public enum ManCheckStatusEnum {

    CHECK_DEFAULT(0, "初始状态"),
    CHECK_SUCCESS(1, "审核通过"),
    CHECK_WAIT(2, "待人工审核"),
    CHECK_FAIL(3, "人工审核未通过"),
    ;

    private Integer value;

    private String desc;

    ManCheckStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
