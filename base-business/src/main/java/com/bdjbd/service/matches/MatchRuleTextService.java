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
 * @className MatchRuleTextService
 * @description TODO
 * @date 2020/3/5
 **/
public interface MatchRuleTextService {

    /**
     * text格式校验
     * @param clauseItems
     * @param record
     * @return
     */
    ResultClauseItem check(List<ResultClauseItem> result, ClauseItem current, List<ClauseItem> clauseItems, Clause clause, QaStandardRecord record, List<RecordItemGroup> itemGroups);
}
