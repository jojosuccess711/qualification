package com.bdjbd.service.matches;

import com.bdjbd.bo.Clause;
import com.bdjbd.bo.ClauseItem;
import com.bdjbd.bo.RecordItemGroup;
import com.bdjbd.bo.ResultClauseItem;
import com.bdjbd.dao.entity.QaStandardRecord;

import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className MatchRuleService
 * @description TODO
 * @date 2020/3/4
 **/
public interface MatchRuleService {

    /**
     * select格式校验
     * @param clauseItems
     * @param record
     * @return
     */
    List<ResultClauseItem> check(List<ClauseItem> clauseItems, Clause clause, QaStandardRecord record);

    /**
     * 校验
     * @param record
     * @return
     */
    ResultClauseItem checkSingle(ClauseItem next, List<ResultClauseItem> result, List<ClauseItem> clauseItems, Clause clause, QaStandardRecord record, List<RecordItemGroup> itemGroups);
}
