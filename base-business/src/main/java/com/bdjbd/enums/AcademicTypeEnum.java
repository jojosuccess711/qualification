package com.bdjbd.enums;

/**
 * @Description:
 * @Author zekunsong
 * @CreateDate 2020/3/24
 * @Version V1.0
 */
public enum AcademicTypeEnum {
    ACADEMIC_TYPE_1(100, "续任初职"),

    ACADEMIC_TYPE_2(101, "初职晋中职"),

    ACADEMIC_TYPE_3(110, "续任中职"),

    ACADEMIC_TYPE_4(111, "中职晋副高"),

    ACADEMIC_TYPE_5(120, "续任副高"),

    ACADEMIC_TYPE_6(121, "副高晋正高"),

    ACADEMIC_TYPE_7(130, "续任正高"),

    ACADEMIC_TYPE_8(200, "未知类型");


    private int key;

    private String value;

    // 构造方法
    AcademicTypeEnum(int key, String value) {
        this.value = value;
        this.key = key;
    }

    // 普通方法
    public static String getValue(int key) {
        for (AcademicTypeEnum c : AcademicTypeEnum.values()) {
            if (c.getKey() == key) {
                return c.value;
            }
        }
        return null;
    }

    // 普通方法
    public static AcademicTypeEnum getByValue(String value) {
        switch (value) {
            case "续任初职":
                return AcademicTypeEnum.ACADEMIC_TYPE_1;
            case "初职晋中职":
                return AcademicTypeEnum.ACADEMIC_TYPE_2;
            case "续任中职":
                return AcademicTypeEnum.ACADEMIC_TYPE_3;
            case "中职晋副高":
                return AcademicTypeEnum.ACADEMIC_TYPE_4;
            case "续任副高":
                return AcademicTypeEnum.ACADEMIC_TYPE_5;
            case "副高晋正高":
                return AcademicTypeEnum.ACADEMIC_TYPE_6;
            case "续任正高":
                return AcademicTypeEnum.ACADEMIC_TYPE_7;
            default:
                return AcademicTypeEnum.ACADEMIC_TYPE_8;
        }
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
