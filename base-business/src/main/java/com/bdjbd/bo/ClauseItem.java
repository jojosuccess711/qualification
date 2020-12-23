package com.bdjbd.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;

/**
 * @author dbc
 * @version 1.0
 * @className ClauseItem 条件项子项 （概念定义： 条件子项）
 * @description TODO
 * @date 2020/2/28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClauseItem {

    /** 条件子项id */
    private String itemId;

    /** 条件子项编号 */
    private String itemCode;

    /** 条件子项类型 */
    private String itemType;

    /** 条件子项类型校验（关联值） */
    private String typeValidate;

    /** 条件项id */
    private String clauseId;

    /** 关联表值 */
    private String relationValue;

    /** 关联表id */
    private String itemRelationId;

    /** 关联条件表达式 */
    private String itemExpress;

    /** 定义编号 (与关系 code相同， 或关系 code 唯一) (eg: A1 B1 C1 ...)*/
    private String itemRelationCode;

    /** 扩展属性0 */
    private String itemAttr0;

    /** 扩展属性1 */
    private String itemAttr1;

    /** 扩展属性2 */
    private String itemAttr2;

    /** 扩展属性3 */
    private String itemAttr3;

    /** 扩展属性4 */
    private String itemAttr4;

    /**
     * 当前值
     */
    @Transient
    private String currentValue;

    /** 关联表name */
    @Transient
    private String itemRelationName;

    /** 关联表type name */
    @Transient
    private String itemRelationTypeName;
}
