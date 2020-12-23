package com.bdjbd.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author dbc
 * @version 1.0
 * @className ResultCategory
 * @description TODO
 * @date 2020/3/5
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultCategory {

    /** 匹配结果 */
    private Boolean result;

    /** 职称 */
    private Category category;

    /** 条件组 */
    private List<ClauseCategory> clauseCategories;

    /** 匹配结果 */
    private List<ResultClauseCategory> resultClauseCategories;

    private Map<String, Boolean> innerGroupResult;
}
