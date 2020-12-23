package com.bdjbd.enums;

/**
 * @author zhuzhe
 * @date 2020/3/25 10:36
 * @email zhuzhe_mail@163.com
 */
public enum CategoryNameEnum {

    CATEGORY_0(0, "初职", "初职"),
    CATEGORY_1(1, "中职", "中职"),
    CATEGORY_2(2, "副高", "副高职"),
    CATEGORY_3(3, "正高", "正高职"),
    ;

    private Integer id;

    private String name;

    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    CategoryNameEnum(Integer id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public static CategoryNameEnum getById(Integer id) {
        for (CategoryNameEnum value : CategoryNameEnum.values()) {
            if (value.getId() == id) {
                return value;
            }
        }
        return null;
    }

    public static CategoryNameEnum getByName(String name) {
        for (CategoryNameEnum value : CategoryNameEnum.values()) {
            if (value.getName().equals(name)) {
                return value;
            }
        }
        return null;
    }

    public static CategoryNameEnum getByValue(String value) {
        for (CategoryNameEnum categoryNameEnum : CategoryNameEnum.values()) {
            if (categoryNameEnum.getValue().equals(value)) {
                return categoryNameEnum;
            }
        }
        return null;
    }
}
