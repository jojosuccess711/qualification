package com.bdjbd.bo;

import com.bdjbd.service.matches.util.CheckResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className ResultClauseCategory 条件结果
 * @description TODO
 * @date 2020/3/5
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultClauseCategory extends CheckResult {

    /** 条件 */
    private ClauseCategory clauseCategory;

    /** 条件项列表 */ //ResultClause
    private List<CheckResult> resultClauseList;
}
