package com.bdjbd.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className Category 五大领域、岗位、职称 （注释中概念定义为：分类）
 * @description TODO
 * @date 2020/2/28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    /** 主键id */
    private String id;

    /** 名称 */
    private String name;

    /** 标题 */
    private String title;

    /** 描述 */
    private String description;

    /** 层级 */
    private Integer grade;

    /** 子列表 */
    private List<Category> children;

    /** 条件组 */
    private List<ClauseCategory> clauseCategories;
}
