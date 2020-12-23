package com.bdjbd.enums;

/**
 * @author zhuzhe
 * @date 2020/3/25 14:27
 * @email zhuzhe_mail@163.com
 */
public enum AcademicCheckStatusEnum {

    CHECK_SUCCESS(1, "审核通过"),
    CHECK_WAIT(10, "待提交审核（默认）"),
    CHECK_WAIT_COMPUTER(20, "待电脑审核"),
    CHECK_FAIL_COMPUTER(21, "电脑审核未通过"),
    CHECK_WAIT_MAN(30, "待人工审核（电脑审核通过）"),
    CHECK_FAIL_MAN(31, "人工审核未通过"),
    ;

    private Integer value;

    private String desc;

    AcademicCheckStatusEnum(Integer value, String desc) {
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
