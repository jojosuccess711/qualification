package com.bdjbd.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dbc
 * @version 1.0
 * @className ClauseCategory
 * @description TODO
 * @date 2020/2/28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapperClause {

    private String itemId;

    private String itemCode;

    private String itemType;

    private String typeValidate;

    private String itemAttr0;

    private String itemAttr1;

    private String itemAttr2;

    private String itemAttr3;

    private String itemAttr4;

    private String itemValue;

    private String itemRelationId;

    private String itemExpress;

    private String itemRelationCode;

    private String clauseId;

    private String clauseTitle;

    private String clauseContent;

    private Integer clauseOrder;

    private String clauseCondition;

    private String clauseItemCondition;

    private String clauseCode;

    private String groupId;

    private String groupTitle;

    private String groupContent;

    private String groupCondition;

    private String groupType;

    private String innerGroup;

    private String categoryId;
}
