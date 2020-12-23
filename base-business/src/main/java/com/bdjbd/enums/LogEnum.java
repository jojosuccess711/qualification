package com.bdjbd.enums;

/**
 * @author dbc
 * @version 1.0
 * @className LogEnum
 * @description TODO
 * @date 2019/12/13
 **/
public enum  LogEnum  {

    COUPON_CREATE("创建优惠券"),

    COUPON_UPDATE("更新优惠券"),

    COUPON_CODE_CREATE("创建优惠券码"),

    COUPON_CODE_UPDATE("更新优惠券码"),

    COUPON_CODE_USE("使用优惠券码"),

    ADMIN_CREATE("创建管理员"),

    ADMIN_UPDATE("更新管理员"),

    ADMIN_DELETE("删除管理员"),

    ADMIN_ROLE("变更管理员角色"),

    ADMIN_SUBBRANCH("变更管理员分店"),

    ROLE_CREATE("创建角色"),

    ROLE_UPDATE("更新角色"),

    ROLE_DELETE("删除角色"),

    ROLE_AUTH("编辑角色权限"),

    SUBBRANCH_CREATE("创建分店"),

    SUBBRANCH_UPDATE("更新分店"),

    SUBBRANCH_DELETE("删除分店"),

    SYS_ADMIN_LOGIN("登录日志");

    private String key;

    LogEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
