package com.bdjbd.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

/**
 * @author dbc
 * @version 1.0
 * @className ClauseCategory 条件组
 * @description TODO
 * @date 2020/2/28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClauseCategory {

    /** 条件组id */
    private String groupId;

    /** 条件组标题 */
    private String groupTitle;

    /** 条件组内容 */
    private String groupContent;

    /** 条件组类型 */
    private String groupType;

    /** 组内分组编号(用于组内或关系) */
    private String innerGroup;

    /** 所属分类(职称) */
    private String categoryId;

    /** 条件项关系 A||B||C||D */
    private String clauseCondition;

    /** 条件项 */
    private List<Clause> clauses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClauseCategory category = (ClauseCategory) o;
        return Objects.equals(groupId, category.groupId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), groupId);
    }
}
