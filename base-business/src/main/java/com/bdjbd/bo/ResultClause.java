package com.bdjbd.bo;

import com.bdjbd.service.matches.util.CheckResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className ResultClause
 * @description TODO
 * @date 2020/3/4
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultClause extends CheckResult {

    /** 条件项 */
    private Clause clause;

    /** 匹配组 （ResultClauseItemGroup）*/
    private List<CheckResult> resultClauseItemGroupList;
}
