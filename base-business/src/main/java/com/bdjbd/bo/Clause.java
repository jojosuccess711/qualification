package com.bdjbd.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className Clause 条件组条件项 (概念定义称为：条件项)
 * @description TODO
 * @date 2020/2/28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clause {

    /** 条件项code(A1 B1) */
    private String clauseCode;

    /** 条件项id */
    private String clauseId;

    /** 条件项标题 */
    private String clauseTitle;

    /** 条件项内容 */
    private String clauseContent;

    /** 条件项表达式 */
    private String clauseItemCondition;

    /** 条件项排序 */
    private Integer clauseOrders;

    /** 条件组id */
    private String clauseCategoryId;

    /** 条件项子项列表 */
    private List<ClauseItem> clauseItems;
}
